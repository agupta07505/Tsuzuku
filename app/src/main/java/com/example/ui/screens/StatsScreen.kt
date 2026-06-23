package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Habit
import com.example.ui.HabitViewModel
import com.example.ui.components.StreakHeatmap
import com.example.ui.components.GithubCombinedHeatmap
import com.example.util.StreakCalculator
import com.example.util.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class StatsGlobalStats(
    val habitsCount: Int,
    val totalCheckins: Int,
    val maxCurrentStreak: Int,
    val maxEverStreak: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    viewModel: HabitViewModel,
    modifier: Modifier = Modifier
) {
    val habits by viewModel.habits.collectAsState()
    val logs by viewModel.allLogs.collectAsState()
    
    // Expand state for each habit card
    var expandedHabitId by remember { mutableStateOf<Int?>(null) }
    
    // Auto-expand first habit if available
    LaunchedEffect(habits) {
        if (expandedHabitId == null && habits.isNotEmpty()) {
            expandedHabitId = habits.first().id
        }
    }

    // Global Aggregate Stats
    val globalStats = remember(habits, logs) {
        if (habits.isEmpty()) {
            StatsGlobalStats(0, 0, 0, 0)
        } else {
            val totalCheckins = logs.count { it.isCompleted }
            var maxCurrentStreak = 0
            var maxEverStreak = 0
            habits.forEach { h ->
                val habitLogs = logs.filter { it.habitId == h.id }
                val st = StreakCalculator.calculate(habitLogs, h.createdAt)
                if (st.currentStreak > maxCurrentStreak) {
                    maxCurrentStreak = st.currentStreak
                }
                if (st.maxStreak > maxEverStreak) {
                    maxEverStreak = st.maxStreak
                }
            }
            StatsGlobalStats(habits.size, totalCheckins, maxCurrentStreak, maxEverStreak)
        }
    }

    // Consistency Score calculation
    val consistencyScore = remember(habits, logs) {
        if (habits.isEmpty()) {
            0
        } else {
            val nowMs = System.currentTimeMillis()
            var totalPossibleSlots = 0
            var completedCount = 0
            
            habits.forEach { habit ->
                // Calculate elapsed days since creation (minimum 1 day, maximum 30 days)
                val diffMs = nowMs - habit.createdAt
                val elapsedDays = (diffMs / (24 * 60 * 60 * 1000L)).toInt().coerceIn(1, 30)
                
                // Retrieve all check-ins for this habit in the last 'elapsedDays' days
                val lastNDaysList = DateUtils.getLastNDays(elapsedDays)
                val habitLogs = logs.filter { it.habitId == habit.id && it.isCompleted && it.date in lastNDaysList }
                
                completedCount += habitLogs.size
                totalPossibleSlots += elapsedDays
            }
            
            if (totalPossibleSlots == 0) {
                0
            } else {
                val score = ((completedCount.toFloat() / totalPossibleSlots.toFloat()) * 100f).roundToInt()
                score.coerceIn(0, 100)
            }
        }
    }

    // Past 7 days checkups representation
    val weeklyActivityList = remember(logs) {
        val list = mutableListOf<Pair<String, Boolean>>()
        val cal = Calendar.getInstance()
        val dayFormat = SimpleDateFormat("E", Locale.US)
        
        for (i in 0 until 7) {
            val dateStr = DateUtils.getFormattedDate(cal)
            val dayLabel = dayFormat.format(cal.time).substring(0, 1) // first letter
            val hasCompleted = logs.any { it.date == dateStr && it.isCompleted }
            list.add(Pair(dayLabel, hasCompleted))
            cal.add(Calendar.DAY_OF_YEAR, -1)
        }
        list.reversed()
    }

    // Best performing habits this month sorting
    val sortedHabits = remember(habits, logs) {
        habits.map { h ->
            val habitLogs = logs.filter { it.habitId == h.id }
            val st = StreakCalculator.calculate(habitLogs, h.createdAt)
            Pair(h, st.completionRate.roundToInt())
        }.sortedByDescending { it.second }
    }

    // High fidelity sorted habits based on actual sorted habits
    val topHabitsShowList = remember(sortedHabits) {
        if (sortedHabits.isEmpty()) {
            emptyList()
        } else {
            sortedHabits.mapIndexed { index, pair ->
                val medal = when (index) {
                    0 -> "🥇"
                    1 -> "🥈"
                    2 -> "🥉"
                    else -> "🎖️"
                }
                Triple(pair.first.name, pair.second, medal)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header: Tsuzuku Insights
            item {
                Column {
                    Text(
                        text = "Tsuzuku Insights",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Realtime trajectory models and progress checkups",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // [ Habits ] [ Check-ins ] [ Max Streak ] [ Score ] Grid Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Habits
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Habits", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${globalStats.habitsCount}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Check-ins
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Check-ins", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${globalStats.totalCheckins}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Max Streak
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Max Streak", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${globalStats.maxEverStreak}d",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    // Score
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Score", fontSize = 10.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$consistencyScore/100",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // 🔥 Current Streak Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text("🔥", fontSize = 32.sp)
                            Column {
                                Text(
                                    text = "Current Streak",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${globalStats.maxCurrentStreak} Days",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Longest Streak",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "${globalStats.maxEverStreak} Days",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Contribution Heatmap Section
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Contribution Heatmap",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            GithubCombinedHeatmap(
                                logs = logs,
                                onCellToggle = { dateStr, toggleState ->
                                    if (habits.isNotEmpty()) {
                                        viewModel.toggleHabitLog(habits.first().id, dateStr, toggleState)
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // 📈 Weekly Activity Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📈 Weekly Activity",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            weeklyActivityList.forEach { (day, completed) ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .background(if (completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
                                            .border(1.dp, if (completed) Color.Transparent else MaterialTheme.colorScheme.outlineVariant, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = day,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (completed) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 🏆 Top Habits Section
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🏆 Top Habits This Month",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        if (habits.isEmpty()) {
                            Text(
                                text = "Create habits on the tracker to see top performing habits.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            topHabitsShowList.take(3).forEach { (name, rate, medal) ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(medal, fontSize = 18.sp)
                                        Text(name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                    }
                                    Text("$rate%", fontSize = 14.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
                                }
                            }
                        }
                    }
                }
            }

            // 💡 Insights Section
            item {
                Text(
                    text = "💡 Insights",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Expanding list of habits
            if (habits.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Create habits on the tracker to unlock trajectory insights.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                items(habits) { habit ->
                    val isExpanded = expandedHabitId == habit.id
                    val habitLogs = logs.filter { it.habitId == habit.id }
                    val stats = remember(habitLogs) {
                        StreakCalculator.calculate(habitLogs, habit.createdAt)
                    }
                    
                    val habitColor = remember(habit.colorHex) {
                        try {
                            Color(android.graphics.Color.parseColor(habit.colorHex))
                        } catch (e: Exception) {
                            Color(0xFF2CB5C3)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("stats_habit_card_${habit.id}"),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = if (isExpanded) 1.5.dp else 1.dp,
                            color = if (isExpanded) habitColor else MaterialTheme.colorScheme.outlineVariant
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedHabitId = if (isExpanded) null else habit.id
                                }
                                .padding(16.dp)
                        ) {
                            // Header of the Card
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Emoji
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(habitColor.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val iconParts = habit.iconName.split(" ")
                                    val emoji = if (iconParts.isNotEmpty()) iconParts[0] else "⭐"
                                    Text(text = emoji, fontSize = 16.sp)
                                }
                                
                                Spacer(modifier = Modifier.width(12.dp))
                                
                                // Title & Success Rate Info (Explicitly formatted as requested)
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${habit.name} - Success Rate: ${stats.completionRate.roundToInt()}%",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "🔥 Running: ${stats.currentStreak}d  |  🏆 High: ${stats.maxStreak}d",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                
                                // Circular rate Badge
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(habitColor.copy(alpha = 0.12f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${stats.completionRate.roundToInt()}%",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Black,
                                        color = habitColor
                                    )
                                }
                            }
                            
                            // Expanding Heatmap Container
                            AnimatedVisibility(visible = isExpanded) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    HorizontalDivider(color = Color(0xFF16222F))
                                    Spacer(modifier = Modifier.height(12.dp))
                                    
                                    Text(
                                        text = "Interactive Calendar Heatmap",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                    
                                    // Heatmap Calendar view
                                    StreakHeatmap(
                                        habit = habit,
                                        logs = habitLogs,
                                        onCellToggle = { dateStr, state ->
                                            viewModel.toggleHabitLog(habit.id, dateStr, state)
                                        }
                                    )
                                    
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "💡 Tap calendar day numbers to adjust check-in logs retroactively.",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.61f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
