package com.agupta07505.tsuzuku.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.agupta07505.tsuzuku.data.Habit
import java.util.Calendar

object HabitNotificationHelper {

    data class QuotePair(val english: String, val japanese: String)

    val mantras = listOf(
        QuotePair("Consistency builds strength.", "続けることが、力になる。"),
        QuotePair("Small steps every day, big change always.", "毎日の小さな一歩が、大きな変化を生む。"),
        QuotePair("Fall seven times, stand up eight.", "七転び八起き。"),
        QuotePair("With patience, the grass becomes milk.", "忍耐があれば、草もミルクになる。"),
        QuotePair("The water is clean, the mind is clear, the day is new.", "水は清く、心は澄み、日は新しい。"),
        QuotePair("Beginning is easy, continuing is hard.", "始めるのは容易、続けるのは困難。")
    )

    fun updatePermanentNotification(context: Context) {
        val sharedPrefs = context.getSharedPreferences("streak_marker_prefs", Context.MODE_PRIVATE)
        val isActive = sharedPrefs.getBoolean("permanent_notification_active", false)
        if (!isActive) return

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "permanent_motivation"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Permanent Motivation Status",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Ongoing decorative status bar notifications."
            }
            notificationManager.createNotificationChannel(channel)
        }

        val activityIntent = Intent(context, com.agupta07505.tsuzuku.MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            10101,
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val mantraIdx = currentHour % mantras.size
        val mantra = mantras[mantraIdx]

        val showJapanese = sharedPrefs.getBoolean("show_japanese_quotes", true)
        val titleText = "Tsuzuku Permanent Motivation"
        val contentText = if (showJapanese) {
            "${mantra.english}\n${mantra.japanese}"
        } else {
            mantra.english
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(com.agupta07505.tsuzuku.R.drawable.ic_tsuzuku_notification)
            .setContentTitle(titleText)
            .setContentText(contentText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(contentText))
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()

        notificationManager.notify(10101, notification)
    }

    fun cancelPermanentNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(10101)
        cancelHourlyUpdate(context)
    }

    fun scheduleNextHourlyUpdate(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            action = "com.agupta07505.tsuzuku.ACTION_UPDATE_HOURLY_NOTIFICATION"
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            20202,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val nextHour = Calendar.getInstance().apply {
            add(Calendar.HOUR_OF_DAY, 1)
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    nextHour.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    nextHour.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                nextHour.timeInMillis,
                pendingIntent
            )
        }
    }

    fun cancelHourlyUpdate(context: Context) {
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReminderReceiver::class.java).apply {
                action = "com.agupta07505.tsuzuku.ACTION_UPDATE_HOURLY_NOTIFICATION"
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                20202,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun scheduleDailyReminder(context: Context, habit: Habit) {
        val hour = habit.reminderHour ?: return
        val minute = habit.reminderMinute ?: return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("habit_id", habit.id)
            putExtra("habit_name", habit.name)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            habit.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1)
            }
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
            Log.d("NotificationHelper", "Scheduled reminder for ${habit.name} at ${hour}:${minute}")
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to inexact repeating if exact fails due to exact alarm permissions on Android 12+
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }

    fun cancelReminder(context: Context, habitId: Int) {
        try {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                habitId,
                intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
            )
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Trigger immediate mock notification for user exploration & quick feedback
    fun triggerImmediateMockNotification(context: Context, habitName: String) {
        try {
            val intent = Intent(context, ReminderReceiver::class.java).apply {
                putExtra("habit_id", 9999)
                putExtra("habit_name", habitName)
            }
            context.sendBroadcast(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
