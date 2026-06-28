/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

data class CountdownDayInfo(
    val days: Long,
    val headline: String,
    val label: String,
    val isToday: Boolean,
    val isPassed: Boolean
)

fun countdownDayInfo(targetDateMillis: Long, nowMillis: Long = System.currentTimeMillis()): CountdownDayInfo {
    val today = startOfLocalDay(nowMillis)
    val target = startOfLocalDay(targetDateMillis)
    val diff = TimeUnit.MILLISECONDS.toDays(target - today)
    return when {
        diff > 0 -> CountdownDayInfo(diff, diff.toString(), "Days Left", isToday = false, isPassed = false)
        diff == 0L -> CountdownDayInfo(0L, "Today!", "Today", isToday = true, isPassed = false)
        else -> CountdownDayInfo(-diff, (-diff).toString(), "Days Ago", isToday = false, isPassed = true)
    }
}

fun formatCountdownDate(millis: Long): String =
    SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(millis))

fun startOfLocalDay(millis: Long): Long {
    return Calendar.getInstance().apply {
        timeInMillis = millis
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}
