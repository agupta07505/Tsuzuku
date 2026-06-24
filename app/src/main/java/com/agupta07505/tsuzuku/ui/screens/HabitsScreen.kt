package com.agupta07505.tsuzuku.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.data.Habit
import com.agupta07505.tsuzuku.data.HabitLog
import com.agupta07505.tsuzuku.ui.HabitViewModel
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import com.agupta07505.tsuzuku.util.DateUtils
import com.agupta07505.tsuzuku.util.StreakCalculator
import com.agupta07505.tsuzuku.util.StreakStats
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

private enum class HabitFilter(val label: String) {
    ALL("All Habits"), ACTIVE("Active"), ARCHIVED("Archived"), COMPLETED("Completed")
}

private enum class HabitSort(val label: String) {
    CONSISTENT("Most Consistent"), STREAK("Longest Streak"), NEWEST("Newest"),
    OLDEST("Oldest"), ALPHABETICAL("Alphabetical")
}

private data class HabitOverview(
    val activeCount: Int,
    val bestStreak: Int,
    val weeklySuccess: Int,
    val totalCompletions: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitsScreen(
    modifier: Modifier = Modifier,
    viewModel: HabitViewModel = viewModel()
) {
    val habits by viewModel.managedHabits.collectAsState()
    val logs by viewModel.allLogs.collectAsState()
    var selectedFilter by rememberSaveable { mutableStateOf(HabitFilter.ALL) }
    var selectedSort by rememberSaveable { mutableStateOf(HabitSort.CONSISTENT) }
    var searchVisible by rememberSaveable { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var editingHabit by remember { mutableStateOf<Habit?>(null) }
    var deleteCandidate by remember { mutableStateOf<Habit?>(null) }

    val statsByHabit = remember(habits, logs) {
        habits.associate { habit ->
            habit.id to StreakCalculator.calculate(logs.filter { it.habitId == habit.id }, habit.createdAt)
        }
    }
    val overview = remember(habits, logs, statsByHabit) {
        calculateOverview(habits, logs, statsByHabit)
    }
    val visibleHabits = remember(habits, logs, selectedFilter, selectedSort, searchQuery, statsByHabit) {
        val completedToday = logs.asSequence()
            .filter { it.isCompleted && it.date == DateUtils.getTodayString() }
            .map { it.habitId }
            .toSet()
        habits.asSequence()
            .filter { habit ->
                when (selectedFilter) {
                    HabitFilter.ALL -> true
                    HabitFilter.ACTIVE -> !habit.isArchived
                    HabitFilter.ARCHIVED -> habit.isArchived
                    HabitFilter.COMPLETED -> habit.id in completedToday
                }
            }
            .filter { habit ->
                searchQuery.isBlank() || habit.name.contains(searchQuery, ignoreCase = true) ||
                    habit.description.contains(searchQuery, ignoreCase = true)
            }
            .let { sequence ->
                when (selectedSort) {
                    HabitSort.CONSISTENT -> sequence.sortedByDescending { statsByHabit[it.id]?.completionRate ?: 0f }
                    HabitSort.STREAK -> sequence.sortedByDescending { statsByHabit[it.id]?.maxStreak ?: 0 }
                    HabitSort.NEWEST -> sequence.sortedByDescending { it.createdAt }
                    HabitSort.OLDEST -> sequence.sortedBy { it.createdAt }
                    HabitSort.ALPHABETICAL -> sequence.sortedBy { it.name.lowercase(Locale.getDefault()) }
                }
            }
            .toList()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(18.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            HabitsHeader(
                searchVisible = searchVisible,
                searchQuery = searchQuery,
                onSearchChanged = { searchQuery = it },
                onToggleSearch = {
                    searchVisible = !searchVisible
                    if (!searchVisible) searchQuery = ""
                },
                onAdd = { showAddDialog = true }
            )
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    HabitStatsCard("Total Habits", overview.activeCount.toString(), "Active", Icons.Default.List, Modifier.weight(1f))
                    HabitStatsCard("Best Streak", overview.bestStreak.toString(), "Days", Icons.Default.LocalFireDepartment, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    HabitStatsCard("Avg. Success", "${overview.weeklySuccess}%", "This Week", Icons.Default.TrendingUp, Modifier.weight(1f))
                    HabitStatsCard("Completed", overview.totalCompletions.toString(), "All Time", Icons.Default.CheckCircle, Modifier.weight(1f))
                }
            }
        }
        item {
            HabitFilterChips(selectedFilter = selectedFilter, onSelected = { selectedFilter = it })
        }
        item {
            HabitSortRow(
                selectedSort = selectedSort,
                onSortSelected = { selectedSort = it },
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )
        }
        if (visibleHabits.isEmpty()) {
            item {
                EmptyHabitsState(
                    hasAnyHabits = habits.isNotEmpty(),
                    onAdd = { showAddDialog = true }
                )
            }
        } else {
            items(visibleHabits, key = { it.id }) { habit ->
                HabitManagementCard(
                    habit = habit,
                    stats = statsByHabit[habit.id] ?: StreakStats(0, 0, 0, 0f),
                    onClick = { editingHabit = habit },
                    onEdit = { editingHabit = habit },
                    onArchive = { viewModel.setHabitArchived(habit, !habit.isArchived) },
                    onDelete = { deleteCandidate = habit }
                )
            }
        }
        item { Spacer(Modifier.height(4.dp)) }
    }

    if (showAddDialog) {
        HabitDialog(
            onDismiss = { showAddDialog = false },
            onSave = { name, description, color, icon, hour, minute ->
                viewModel.addHabit(name, description, color, icon, hour, minute)
                showAddDialog = false
            }
        )
    }

    editingHabit?.let { habit ->
        HabitDialog(
            habit = habit,
            onDismiss = { editingHabit = null },
            onSave = { name, description, color, icon, hour, minute ->
                viewModel.updateHabit(
                    habit.copy(
                        name = name,
                        description = description,
                        colorHex = color,
                        iconName = icon,
                        reminderHour = hour,
                        reminderMinute = minute
                    )
                )
                editingHabit = null
            },
            onDelete = {
                editingHabit = null
                deleteCandidate = habit
            }
        )
    }

    deleteCandidate?.let { habit ->
        AlertDialog(
            onDismissRequest = { deleteCandidate = null },
            icon = { Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error) },
            title = { Text("Delete ${habit.name}?") },
            text = { Text("This permanently removes the habit and all of its check-in history.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteHabit(habit)
                        deleteCandidate = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { deleteCandidate = null }) { Text("Cancel") } }
        )
    }
}

@Composable
private fun HabitsHeader(
    searchVisible: Boolean,
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onAdd: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Your Habits", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(
                    "Build consistent habits. Become 1% better every day.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(onClick = onToggleSearch) {
                Icon(if (searchVisible) Icons.Default.Close else Icons.Default.Search, "Search habits")
            }
            FilledIconButton(
                onClick = onAdd,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) { Icon(Icons.Default.Add, "Add habit") }
        }
        if (searchVisible) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChanged,
                placeholder = { Text("Search habits") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HabitStatsCard(
    title: String,
    value: String,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(7.dp))
                Text(title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(value, fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 7.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun HabitFilterChips(selectedFilter: HabitFilter, onSelected: (HabitFilter) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(HabitFilter.entries) { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onSelected(filter) },
                label = { Text(filter.label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

@Composable
private fun HabitSortRow(
    selectedSort: HabitSort,
    onSortSelected: (HabitSort) -> Unit,
    selectedFilter: HabitFilter,
    onFilterSelected: (HabitFilter) -> Unit
) {
    var sortExpanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(Modifier.weight(1f)) {
            Surface(
                modifier = Modifier.fillMaxWidth().clickable { sortExpanded = true },
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(Modifier.padding(horizontal = 14.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Sort by: ", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                    Text(selectedSort.label, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1f), maxLines = 1)
                    Icon(Icons.Default.KeyboardArrowDown, null)
                }
            }
            DropdownMenu(expanded = sortExpanded, onDismissRequest = { sortExpanded = false }) {
                HabitSort.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        onClick = { onSortSelected(option); sortExpanded = false },
                        leadingIcon = { if (option == selectedSort) Icon(Icons.Default.Check, null) }
                    )
                }
            }
        }
        Box {
            OutlinedButton(onClick = { filterExpanded = true }, shape = RoundedCornerShape(14.dp), contentPadding = PaddingValues(horizontal = 13.dp)) {
                Icon(Icons.Default.Tune, "Filter")
                Spacer(Modifier.width(6.dp))
                Text("Filter")
            }
            DropdownMenu(expanded = filterExpanded, onDismissRequest = { filterExpanded = false }) {
                HabitFilter.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        onClick = { onFilterSelected(option); filterExpanded = false },
                        leadingIcon = { if (option == selectedFilter) Icon(Icons.Default.Check, null) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HabitManagementCard(
    habit: Habit,
    stats: StreakStats,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onArchive: () -> Unit,
    onDelete: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val defaultHabitColor = MaterialTheme.colorScheme.primary
    val habitColor = remember(habit.colorHex, defaultHabitColor) {
        runCatching { Color(android.graphics.Color.parseColor(habit.colorHex)) }
            .getOrDefault(defaultHabitColor)
    }
    val success = stats.completionRate.roundToInt().coerceIn(0, 100)
    val successColor = when {
        success >= 75 -> MaterialTheme.colorScheme.primary
        success >= 45 -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.error
    }
    val created = remember(habit.createdAt) {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(habit.createdAt))
    }
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(habitColor.copy(alpha = .18f)),
                contentAlignment = Alignment.Center
            ) {
                Text(habit.iconName.substringBefore(" ").ifBlank { "🌱" }, fontSize = 22.sp)
            }
            Column(Modifier.padding(start = 11.dp).weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(habit.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                    if (habit.isArchived) {
                        Surface(shape = RoundedCornerShape(6.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                            Text("Archived", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp))
                        }
                    }
                }
                Text("🔥 ${stats.currentStreak} day streak", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                Text("Created: $created", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelSmall)
            }
            SuccessProgress(success, successColor)
            IconButton(onClick = onEdit, modifier = Modifier.size(38.dp)) { Icon(Icons.Default.Edit, "Edit ${habit.name}", modifier = Modifier.size(19.dp)) }
            Box {
                IconButton(onClick = { menuExpanded = true }, modifier = Modifier.size(38.dp)) { Icon(Icons.Default.MoreVert, "More options") }
                DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                    DropdownMenuItem(text = { Text("Edit") }, onClick = { menuExpanded = false; onEdit() }, leadingIcon = { Icon(Icons.Default.Edit, null) })
                    DropdownMenuItem(
                        text = { Text(if (habit.isArchived) "Unarchive" else "Archive") },
                        onClick = { menuExpanded = false; onArchive() },
                        leadingIcon = { Icon(if (habit.isArchived) Icons.Default.Unarchive else Icons.Default.Archive, null) }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete", color = MaterialTheme.colorScheme.error) },
                        onClick = { menuExpanded = false; onDelete() },
                        leadingIcon = { Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SuccessProgress(percent: Int, color: Color) {
    Box(Modifier.size(55.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize().padding(5.dp)) {
            val width = 5.dp.toPx()
            drawCircle(color.copy(alpha = .18f), style = Stroke(width))
            drawArc(color, -90f, 360f * percent / 100f, false, style = Stroke(width, cap = StrokeCap.Round))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$percent%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = color)
            Text("Success", fontSize = 7.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun EmptyHabitsState(hasAnyHabits: Boolean, onAdd: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 48.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
            Text(if (hasAnyHabits) "🔎" else "🌱", fontSize = 46.sp, modifier = Modifier.padding(22.dp))
        }
        Text(if (hasAnyHabits) "No Matching Habits" else "No Habits Yet", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 18.dp))
        Text(
            if (hasAnyHabits) "Try a different search or filter."
            else "Tap + to create your first habit and start building consistency.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
        if (!hasAnyHabits) {
            Button(onClick = onAdd, modifier = Modifier.padding(top = 18.dp)) {
                Icon(Icons.Default.Add, null)
                Spacer(Modifier.width(7.dp))
                Text("Add Habit")
            }
        }
    }
}

private fun calculateOverview(
    habits: List<Habit>,
    logs: List<HabitLog>,
    statsByHabit: Map<Int, StreakStats>
): HabitOverview {
    val activeHabits = habits.filterNot { it.isArchived }
    val lastWeek = (0..6).map { DateUtils.getDateWithOffset(-it) }.toSet()
    val weeklyRates = activeHabits.map { habit ->
        val ageDays = (((System.currentTimeMillis() - habit.createdAt).coerceAtLeast(0L)) / 86_400_000L + 1).toInt()
        val eligibleDays = ageDays.coerceIn(1, 7)
        val completed = logs.count { it.habitId == habit.id && it.isCompleted && it.date in lastWeek }
        (completed.coerceAtMost(eligibleDays) * 100f / eligibleDays).roundToInt()
    }
    return HabitOverview(
        activeCount = activeHabits.size,
        bestStreak = statsByHabit.values.maxOfOrNull { it.maxStreak } ?: 0,
        weeklySuccess = if (weeklyRates.isEmpty()) 0 else weeklyRates.average().roundToInt(),
        totalCompletions = logs.count { it.isCompleted }
    )
}

@Preview(showBackground = true)
@Composable
private fun HabitManagementCardPreview() {
    MyApplicationTheme(themePreference = "green") {
        Box(Modifier.background(MaterialTheme.colorScheme.background).padding(16.dp)) {
            HabitManagementCard(
                habit = Habit(name = "Codeforces", description = "Solve one problem", iconName = "💻 Code", createdAt = 1_704_844_800_000),
                stats = StreakStats(currentStreak = 15, maxStreak = 21, totalCompletions = 42, completionRate = 85f),
                onClick = {}, onEdit = {}, onArchive = {}, onDelete = {}
            )
        }
    }
}
