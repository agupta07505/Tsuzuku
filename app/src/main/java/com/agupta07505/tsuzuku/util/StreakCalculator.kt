/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.util

import com.agupta07505.tsuzuku.data.HabitLog
import java.util.Date
import kotlin.math.roundToInt

data class StreakStats(
    val currentStreak: Int,
    val maxStreak: Int,
    val totalCompletions: Int,
    val completionRate: Float
)

object StreakCalculator {
    fun calculate(logs: List<HabitLog>, creationTime: Long): StreakStats {
        val completedLogs = logs.filter { it.isCompleted }.distinctBy { it.date }
        if (completedLogs.isEmpty()) {
            return StreakStats(0, 0, 0, 0f)
        }

        // Sort ascending: oldest first
        val sortedDates = completedLogs.map { DateUtils.parseDateString(it.date) }
            .filterNotNull()
            .sorted()

        if (sortedDates.isEmpty()) {
            return StreakStats(0, 0, 0, 0f)
        }

        // Calculate Max Streak
        val msInDay = 24 * 60 * 60 * 1000L
        var maxStreak = 0
        var tempStreak = 0
        var lastDate: Date? = null

        for (date in sortedDates) {
            if (lastDate == null) {
                tempStreak = 1
            } else {
                val diffMs = date.time - lastDate.time
                val diffDays = (diffMs.toDouble() / msInDay).roundToInt()
                
                if (diffDays == 1) {
                    tempStreak++
                } else if (diffDays > 1) {
                    if (tempStreak > maxStreak) {
                        maxStreak = tempStreak
                    }
                    tempStreak = 1
                }
            }
            lastDate = date
        }
        if (tempStreak > maxStreak) {
            maxStreak = tempStreak
        }

        // Calculate Current Streak
        val todayStr = DateUtils.getTodayString()
        val yesterdayStr = DateUtils.getDateWithOffset(-1)

        val lastCompletedDateStr = completedLogs.map { it.date }.sorted().lastOrNull()
        var currentStreak = 0
        
        if (lastCompletedDateStr == todayStr || lastCompletedDateStr == yesterdayStr) {
            val activeSorted = sortedDates.reversed() // newest first
            var prevDate = activeSorted.first()
            currentStreak = 1
            
            for (i in 1 until activeSorted.size) {
                val currentDate = activeSorted[i]
                val diffMs = prevDate.time - currentDate.time
                val diffDays = (diffMs.toDouble() / msInDay).roundToInt()
                
                if (diffDays == 1) {
                    currentStreak++
                    prevDate = currentDate
                } else {
                    break
                }
            }
        }

        // Completion Rate over last 30 days relative to today
        val last30Days = DateUtils.getLast30Days()
        val completedInLast30 = completedLogs.count { last30Days.contains(it.date) }
        
        val msSinceCreation = System.currentTimeMillis() - creationTime
        val daysSinceCreation = (msSinceCreation.toDouble() / msInDay).roundToInt() + 1
        val denominator = minOf(30, maxOf(1, daysSinceCreation))
        val completionRate = minOf(100f, (completedInLast30.toFloat() / denominator.toFloat()) * 100f)

        return StreakStats(
            currentStreak = currentStreak,
            maxStreak = maxStreak,
            totalCompletions = completedLogs.size,
            completionRate = completionRate
        )
    }
}
