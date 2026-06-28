/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.agupta07505.tsuzuku.MainActivity
import com.agupta07505.tsuzuku.data.Quotes

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == "com.agupta07505.tsuzuku.ACTION_UPDATE_HOURLY_NOTIFICATION") {
            HabitNotificationHelper.updatePermanentNotification(context)
            HabitNotificationHelper.scheduleNextHourlyUpdate(context)
            return
        }

        val habitName = intent.getStringExtra("habit_name") ?: "Your Habits"
        val habitId = intent.getIntExtra("habit_id", -1)
        
        showNotification(context, habitId, habitName)
    }

    private fun showNotification(context: Context, habitId: Int, habitName: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "daily_reminders"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Habit Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminds you to check in and mark your habits completed."
            }
            notificationManager.createNotificationChannel(channel)
        }

        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            habitId.hashCode(),
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Read preferences for quotes
        val sharedPrefs = context.getSharedPreferences("streak_marker_prefs", Context.MODE_PRIVATE)
        val showQuotes = sharedPrefs.getBoolean("quotes_active", true)
        val showJapanese = sharedPrefs.getBoolean("show_japanese_quotes", true)

        var contentText = "Don't break your streak! Check in for \"$habitName\" today."
        
        if (showQuotes) {
            val quote = Quotes.next(context)
            contentText = if (showJapanese) {
                "${quote.english}\n${quote.japanese}"
            } else {
                quote.english
            }
        }

        // Native beautiful checkmark alarm symbol or similar
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(com.agupta07505.tsuzuku.R.drawable.ic_tsuzuku_notification)
            .setContentTitle("Check in for \"$habitName\"!")
            .setContentText(contentText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(contentText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(habitId, notification)
    }
}
