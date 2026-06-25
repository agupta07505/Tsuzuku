/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agupta07505.tsuzuku.data.Habit
import com.agupta07505.tsuzuku.data.HabitLog
import com.agupta07505.tsuzuku.util.DateUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun StreakHeatmap(
    habit: Habit,
    logs: List<HabitLog>,
    onCellToggle: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    weeksCount: Int = 18,
    selectedYear: Int? = null,
    title: String? = null,
    allowPastToggle: Boolean = false
) {
    val todayStr = remember { DateUtils.getTodayString() }
    val effectiveWeeksCount = if (selectedYear == null) weeksCount else 53
    
    // Retrieve a structured sequence of dates for the grid
    val dates = remember(weeksCount, selectedYear) {
         val tempDates = mutableListOf<String>()
         val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
         val cal = Calendar.getInstance()

         if (selectedYear != null) {
             cal.clear()
             cal.set(selectedYear, Calendar.JANUARY, 1)
         }
         val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
         val daysToMonday = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - 2
         cal.add(Calendar.DAY_OF_YEAR, -daysToMonday)
         if (selectedYear == null) cal.add(Calendar.WEEK_OF_YEAR, -(weeksCount - 1))
         
         for (i in 0 until (effectiveWeeksCount * 7)) {
             tempDates.add(sdf.format(cal.time))
             cal.add(Calendar.DAY_OF_YEAR, 1)
         }
         tempDates
    }

    val habitColor = try {
        Color(android.graphics.Color.parseColor(habit.colorHex))
    } catch (e: Exception) {
        MaterialTheme.colorScheme.primary
    }

    Column(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title ?: selectedYear?.let { "$it Activity" } ?: "Last $weeksCount Weeks Activity",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f, fill = false)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // GitHub 7-rows × 18-weeks block columns
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                for (c in 0 until effectiveWeeksCount) {
                    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        for (r in 0 until 7) {
                            val idx = c * 7 + r
                            val dateStr = dates.getOrNull(idx) ?: ""
                            val isToday = dateStr == todayStr
                            val isCompleted = logs.any { it.date == dateStr && it.isCompleted }
                            val isFuture = dateStr > todayStr
                            val isOutsideYear = selectedYear != null && !dateStr.startsWith(selectedYear.toString())
                            val canToggle = !isFuture && !isOutsideYear && (allowPastToggle || isToday)
                            val cellColor = when {
                                isOutsideYear || isFuture -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.28f)
                                isCompleted -> habitColor
                                isToday -> habitColor.copy(alpha = 0.22f)
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.82f)
                            }
                            
                            val cellBorder = if (isToday) {
                                Modifier.border(1.4.dp, habitColor, RoundedCornerShape(5.dp))
                            } else Modifier

                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(cellColor)
                                    .then(cellBorder)
                                    .clickable(enabled = canToggle) {
                                        onCellToggle(dateStr, !isCompleted)
                                    }
                                    .testTag("github_cell_${dateStr}_${habit.id}"),
                                contentAlignment = Alignment.Center
                            ) {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentMonthHabitHeatmap(
    habit: Habit,
    logs: List<HabitLog>,
    onTodayToggle: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val todayStr = remember { DateUtils.getTodayString() }
    val calendar = remember { Calendar.getInstance() }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val monthDates = remember(year, month) { DateUtils.getMonthGridDates(year, month) }
    val rows = remember(monthDates) { monthDates.chunked(7) }
    val completedDates = remember(logs) {
        logs.filter { it.isCompleted }.map { it.date }.toSet()
    }
    val habitColor = try {
        Color(android.graphics.Color.parseColor(habit.colorHex))
    } catch (e: Exception) {
        MaterialTheme.colorScheme.primary
    }
    val emptyColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.88f)
    val futureColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.32f)

    Column(modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Text(
            text = "This Month Activity",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            rows.forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    week.forEach { dateStr ->
                        if (dateStr == null) {
                            Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                        } else {
                            val isToday = dateStr == todayStr
                            val isCompleted = dateStr in completedDates
                            val isFuture = dateStr > todayStr
                            val cellColor = when {
                                isCompleted -> habitColor
                                isFuture -> futureColor
                                else -> emptyColor
                            }
                            val border = if (isToday) {
                                Modifier.border(1.6.dp, habitColor, RoundedCornerShape(7.dp))
                            } else {
                                Modifier.border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.55f), RoundedCornerShape(7.dp))
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(cellColor)
                                    .then(border)
                                    .clickable(enabled = isToday) {
                                        onTodayToggle(dateStr, !isCompleted)
                                    }
                                    .testTag("month_cell_${dateStr}_${habit.id}")
                            )
                        }
                    }
                    repeat(7 - week.size) {
                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun GithubCombinedHeatmap(
    logs: List<HabitLog>,
    onCellToggle: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    weeksCount: Int = 18,
    selectedYear: Int? = null
) {
    val todayStr = remember { DateUtils.getTodayString() }
    val effectiveWeeksCount = if (selectedYear == null) weeksCount else 53
    
    // Retrieve grid dates
    val dates = remember(weeksCount, selectedYear) {
         val tempDates = mutableListOf<String>()
         val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
         val cal = Calendar.getInstance()

         if (selectedYear != null) {
             cal.clear()
             cal.set(selectedYear, Calendar.JANUARY, 1)
         }
         val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
         val daysToMonday = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - 2
         
         cal.add(Calendar.DAY_OF_YEAR, -daysToMonday)
         if (selectedYear == null) cal.add(Calendar.WEEK_OF_YEAR, -(weeksCount - 1))
         
         for (i in 0 until (effectiveWeeksCount * 7)) {
             tempDates.add(sdf.format(cal.time))
             cal.add(Calendar.DAY_OF_YEAR, 1)
         }
         tempDates
    }

    // Map each date to count of completions
    val dateCompletionCounts = remember(logs) {
        logs.filter { it.isCompleted }.groupBy { it.date }.mapValues { it.value.size }
    }

    Column(modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(
            text = selectedYear?.let { "$it Combined Contributions" } ?: "Total Combined Contributions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = "Aggregates overall habits daily checked stats as progressive shaded intensities of green.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(end = 6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val weekLabels = listOf("Mon", "", "Wed", "", "Fri", "", "Sun")
                weekLabels.forEach { label ->
                    Box(
                        modifier = Modifier.height(18.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = label,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.61f)
                        )
                    }
                }
            }

            // GitHub Combined Color Shading (Green theme!)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                for (c in 0 until effectiveWeeksCount) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        for (r in 0 until 7) {
                            val idx = c * 7 + r
                            val dateStr = dates.getOrNull(idx) ?: ""
                            val isToday = dateStr == todayStr
                            val count = dateCompletionCounts[dateStr] ?: 0
                            val isFuture = dateStr > todayStr
                            val isOutsideYear = selectedYear != null && !dateStr.startsWith(selectedYear.toString())
                            
                            // GitHub's standard green shades:
                            // 0: Light gray
                            // 1: Lightest Green (#C6E48B) -> Color(0xFFD6F5D6)
                            // 2: Medium Green (#7BC96F)  -> Color(0xFF8CE99A)
                            // 3: Dark Green (#239A3B)    -> Color(0xFF37B24D)
                            // 4+: Deep Forest Green (#196127) -> Color(0xFF2B8A3E)
                            val cellColor = when {
                                isOutsideYear || isFuture -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
                                count >= 4 -> Color(0xFF2B8A3E)
                                count == 3 -> Color(0xFF37B24D)
                                count == 2 -> Color(0xFF8CE99A)
                                count == 1 -> Color(0xFFD6F5D6)
                                isToday -> Color(0xFFE9ECEF)
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                            }
                            
                            val cellBorder = if (isToday) {
                                Modifier.border(1.2.dp, Color(0xFF2B8A3E), RoundedCornerShape(3.dp))
                            } else Modifier

                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(cellColor)
                                    .then(cellBorder)
                                    .clickable(enabled = isToday) {
                                        // Combined cell allows toggle via click?
                                        // Wait, click on combined heatmap toggles? Since it is combined we don't necessarily toggle randomly,
                                        // or we can allow toggling if isToday is true for a special UI. Let's make it interactive or non-click.
                                        // Let's pass toggle logic if they click today's cell.
                                        if (isToday) {
                                            onCellToggle(dateStr, count == 0)
                                        }
                                    }
                                    .testTag("github_combined_${dateStr}"),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isToday) {
                                    Box(
                                        modifier = Modifier
                                            .size(5.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(Color.White)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Heatmap Legend like GitHub
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Less",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            val legendColors = listOf(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                Color(0xFFD6F5D6),
                Color(0xFF8CE99A),
                Color(0xFF37B24D),
                Color(0xFF2B8A3E)
            )
            legendColors.forEach { c ->
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(c)
                )
                Spacer(modifier = Modifier.width(2.dp))
            }
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "More",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
