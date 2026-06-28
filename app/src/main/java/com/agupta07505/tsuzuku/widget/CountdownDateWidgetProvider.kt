/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews
import com.agupta07505.tsuzuku.MainActivity
import com.agupta07505.tsuzuku.R
import com.agupta07505.tsuzuku.data.AppDatabase
import com.agupta07505.tsuzuku.data.CountdownDateEntity
import com.agupta07505.tsuzuku.util.countdownDayInfo
import com.agupta07505.tsuzuku.util.formatCountdownDate
import kotlinx.coroutines.runBlocking

class CountdownDateWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { updateWidget(context, appWidgetManager, it) }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            appWidgetIds.forEach { remove(keyFor(it)) }
        }.apply()
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action in setOf(Intent.ACTION_DATE_CHANGED, Intent.ACTION_TIMEZONE_CHANGED, Intent.ACTION_TIME_CHANGED)) {
            updateAll(context)
        }
    }

    companion object {
        private const val PREFS_NAME = "countdown_date_widget_preferences"

        fun saveCountdownId(context: Context, appWidgetId: Int, countdownId: Long) {
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putLong(keyFor(appWidgetId), countdownId)
                .apply()
        }

        fun updateAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(ComponentName(context, CountdownDateWidgetProvider::class.java))
            ids.forEach { updateWidget(context, manager, it) }
        }

        private fun updateWidget(context: Context, manager: AppWidgetManager, appWidgetId: Int) {
            val countdownId = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getLong(keyFor(appWidgetId), -1L)
            val event = runBlocking {
                val dao = AppDatabase.getDatabase(context).countdownDateDao()
                if (countdownId > 0L) dao.getCountdownByIdDirect(countdownId)
                else {
                    val countdowns = dao.getAllCountdownsDirect()
                    countdowns.firstOrNull { !countdownDayInfo(it.targetDateMillis).isPassed }
                        ?: countdowns.firstOrNull()
                }
            }
            manager.updateAppWidget(appWidgetId, buildViews(context, appWidgetId, event))
        }

        private fun buildViews(context: Context, appWidgetId: Int, event: CountdownDateEntity?): RemoteViews {
            val views = RemoteViews(context.packageName, R.layout.widget_countdown_date)
            if (event == null) {
                views.setTextViewText(R.id.widget_title, "No countdown added")
                views.setTextViewText(R.id.widget_count, "+")
                views.setTextViewText(R.id.widget_label, "Add important date")
                views.setTextViewText(R.id.widget_date, "")
                views.setTextColor(R.id.widget_title, Color.rgb(143, 234, 60))
                views.setTextColor(R.id.widget_count, Color.rgb(143, 234, 60))
            } else {
                val info = countdownDayInfo(event.targetDateMillis)
                val accent = if (info.isPassed) Color.rgb(255, 82, 82) else event.accentColor.toInt()
                views.setTextViewText(R.id.widget_title, event.title)
                views.setTextViewText(R.id.widget_count, info.headline)
                views.setTextViewText(R.id.widget_label, info.label)
                views.setTextViewText(R.id.widget_date, formatCountdownDate(event.targetDateMillis))
                views.setTextColor(R.id.widget_title, accent)
                views.setTextColor(R.id.widget_count, accent)
                views.setTextColor(R.id.widget_date, accent)
            }
            views.setOnClickPendingIntent(R.id.widget_root, openIntent(context, appWidgetId, event?.id ?: -1L))
            return views
        }

        private fun openIntent(context: Context, appWidgetId: Int, countdownId: Long): PendingIntent {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("open_tab", "countdown")
                putExtra("countdown_id", countdownId)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            return PendingIntent.getActivity(
                context,
                appWidgetId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        private fun keyFor(appWidgetId: Int) = "widget_$appWidgetId"
    }
}
