package com.agupta07505.tsuzuku.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.agupta07505.tsuzuku.data.Habit
import com.agupta07505.tsuzuku.ui.HabitViewModel
import com.agupta07505.tsuzuku.ui.components.StreakHeatmap
import com.agupta07505.tsuzuku.util.DateUtils
import com.agupta07505.tsuzuku.util.StreakCalculator
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackerScreen(
    viewModel: HabitViewModel,
    modifier: Modifier = Modifier
) {
    val habits by viewModel.habits.collectAsState()
    val logs by viewModel.allLogs.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    
    var showAddDialog by remember { mutableStateOf(false) }
    var editingHabit by remember { mutableStateOf<Habit?>(null) }
    var viewingStreakHabit by remember { mutableStateOf<Habit?>(null) }
    
    val selectedMantra = remember {
        listOf(
            Pair("Consistency builds strength.", "続けることが、力になる。"),
            Pair("Small steps every day, big change always.", "毎日の小さな一歩が、大きな変化を生む。"),
            Pair("Fall seven times, stand up eight.", "七転び八起き。"),
            Pair("With patience, the grass becomes milk.", "忍耐があれば、草もミルクになる。"),
            Pair("The water is clean, the mind is clear, the day is new.", "水は清く、心は澄み、日は新しい。"),
            Pair("Beginning is easy, continuing is hard.", "始めるのは容易、続けるのは困難。")
        ).random()
    }
    
    // Quick-select week dates
    val weekDates = remember {
        val list = mutableListOf<Pair<String, Date>>()
        val sdf = SimpleDateFormat("EEE", Locale.US)
        val cal = Calendar.getInstance()
        // Last 7 days
        for (i in 0 until 7) {
            val dateStr = DateUtils.getFormattedDate(cal)
            list.add(Pair(dateStr, cal.time))
            cal.add(Calendar.DAY_OF_YEAR, -1)
        }
        list.reversed()
    }

    // Unified global streak calculation across all habits
    val globalStreak = remember(logs) {
        val completedDatesByDay = logs.filter { it.isCompleted }
            .map { it.date }
            .distinct()
            .mapNotNull { DateUtils.parseDateString(it) }
            .sorted()

        if (completedDatesByDay.isEmpty()) {
            0
        } else {
            val msInDay = 24 * 60 * 60 * 1000L
            val todayStr = DateUtils.getTodayString()
            val yesterdayStr = DateUtils.getDateWithOffset(-1)
            val lastDateStr = logs.filter { it.isCompleted }.map { it.date }.distinct().sorted().lastOrNull()

            if (lastDateStr == todayStr || lastDateStr == yesterdayStr) {
                val reversedDates = completedDatesByDay.reversed()
                var current = 1
                var prev = reversedDates.first()
                for (i in 1 until reversedDates.size) {
                    val curr = reversedDates[i]
                    val diffMs = prev.time - curr.time
                    val diffDays = (diffMs.toDouble() / msInDay).roundToInt()
                    if (diffDays == 1) {
                        current++
                        prev = curr
                    } else if (diffDays > 1) {
                        break
                    }
                }
                current
            } else {
                0
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            
            // Premium Brand Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Custom brand ring icon loaded dynamically from Vector Drawable
                    Image(
                        painter = painterResource(id = com.agupta07505.tsuzuku.R.drawable.ic_tsuzuku_logo),
                        contentDescription = "Tsuzuku Logo",
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                    )
                    
                    Column {
                        Text(
                            text = "Tsuzuku",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "続く ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Keep going, every day.",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                // Add button as solid aquamarine circle FAB
                IconButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2CB5C3))
                        .testTag("fab_add_habit")
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Habit",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(18.dp))
            
            // Overall streak banner card with Canvas-drawn bamboo stalk illustration
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                ) {
                    // Bamboo stalk drawing canvas aligned to the right side
                    Canvas(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(100.dp)
                    ) {
                        val stalkColor = Color(0xFF14302E)
                        val leafColor = Color(0xFF0F4D45)
                        
                        // Main thick stalk
                        drawRoundRect(
                            color = stalkColor,
                            topLeft = androidx.compose.ui.geometry.Offset(x = size.width * 0.7f, y = 0f),
                            size = androidx.compose.ui.geometry.Size(width = 8.dp.toPx(), height = size.height),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
                        )
                        
                        // Inner secondary thinner stalk
                        drawRoundRect(
                            color = stalkColor.copy(alpha = 0.6f),
                            topLeft = androidx.compose.ui.geometry.Offset(x = size.width * 0.45f, y = 0f),
                            size = androidx.compose.ui.geometry.Size(width = 5.dp.toPx(), height = size.height),
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.5.dp.toPx())
                        )
                        
                        // Bamboo segment lines on thick stalk
                        for (yRatio in listOf(0.2f, 0.5f, 0.8f)) {
                            drawLine(
                                color = Color(0xFF090E14),
                                start = androidx.compose.ui.geometry.Offset(x = size.width * 0.7f, y = size.height * yRatio),
                                end = androidx.compose.ui.geometry.Offset(x = size.width * 0.7f + 8.dp.toPx(), y = size.height * yRatio),
                                strokeWidth = 2.dp.toPx()
                            )
                        }
                        
                        // Aesthetic plant nodes/leaves
                        val leafPath = androidx.compose.ui.graphics.Path()
                        leafPath.moveTo(size.width * 0.7f, size.height * 0.5f)
                        leafPath.quadraticTo(size.width * 0.55f, size.height * 0.35f, size.width * 0.35f, size.height * 0.45f)
                        leafPath.quadraticTo(size.width * 0.55f, size.height * 0.48f, size.width * 0.7f, size.height * 0.5f)
                        
                        leafPath.moveTo(size.width * 0.7f, size.height * 0.2f)
                        leafPath.quadraticTo(size.width * 0.85f, size.height * 0.1f, size.width * 0.95f, size.height * 0.18f)
                        leafPath.quadraticTo(size.width * 0.85f, size.height * 0.22f, size.width * 0.7f, size.height * 0.2f)

                        drawPath(leafPath, color = leafColor)
                    }
                    
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Flame icon and streak digit
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("🔥", fontSize = 28.sp)
                            Column {
                                Text(
                                    text = "$globalStreak",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Day streak",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        // Slogan motto text
                        Text(
                            text = "Small steps every day,\nbig change always.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(18.dp))
            
            // Tracking Date Slider
            Text(
                text = "Tracking Date",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("date_slider"),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                weekDates.forEach { (dateStr, dateVal) ->
                    val isSelected = dateStr == selectedDate
                    val dayName = SimpleDateFormat("EEE", Locale.US).format(dateVal)
                    val dayNum = SimpleDateFormat("d", Locale.US).format(dateVal)
                    
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected) {
                                        MaterialTheme.colorScheme.primaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                    }
                                )
                                .border(
                                    width = if (isSelected) 1.5.dp else 1.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    viewModel.setSelectedDate(dateStr)
                                }
                                .padding(vertical = 8.dp)
                                .testTag("date_slider_cell_$dateStr"),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = dayName,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = dayNum,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        
                        // Active glowing dot centered underneath Selected Day representation
                        if (isSelected) {
                            Spacer(modifier = Modifier.height(3.dp))
                            Box(
                                modifier = Modifier
                                    .size(5.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Habits list
            if (habits.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(32.dp)
                    ) {
                        Text(
                            text = "🌱",
                            fontSize = 60.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "No Habits Configured Yet",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Click the '+' button to declare your first habit and lock in your daily streaks securely.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .testTag("habits_checklist"),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(habits) { habit ->
                        val habitLogs = logs.filter { it.habitId == habit.id }
                        val stats = remember(habitLogs) {
                            StreakCalculator.calculate(habitLogs, habit.createdAt)
                        }
                        
                        val isCompleted = logs.any { it.habitId == habit.id && it.date == selectedDate && it.isCompleted }
                        
                        val habitColor = remember(habit.colorHex) {
                            try {
                                Color(android.graphics.Color.parseColor(habit.colorHex))
                            } catch (e: Exception) {
                                Color(0xFF2CB5C3)
                            }
                        }

                        Card(
                            onClick = { viewingStreakHabit = habit },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("habit_card_${habit.id}"),
                            shape = RoundedCornerShape(18.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            ),
                            border = BorderStroke(
                                width = if (isCompleted) 1.5.dp else 1.dp,
                                color = if (isCompleted) habitColor else MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Left Emoji Icon Circle with tinted background matching image
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(habitColor.copy(alpha = 0.12f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val iconParts = habit.iconName.split(" ")
                                    val emoji = if (iconParts.isNotEmpty()) iconParts[0] else "⭐"
                                    Text(
                                        text = emoji,
                                        fontSize = 22.sp
                                    )
                                }
                                
                                Spacer(modifier = Modifier.width(14.dp))
                                
                                // Habit Name & Details Text
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = habit.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    
                                    Spacer(modifier = Modifier.height(2.dp))
                                    
                                    // Streak Info with flame icon matching screenshot
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "🔥 ${stats.currentStreak} day streak",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        
                                        if (habit.reminderHour != null && habit.reminderMinute != null) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Default.Notifications,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(10.dp),
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                                )
                                                Spacer(modifier = Modifier.width(2.dp))
                                                val formattedTime = String.format(
                                                    "%02d:%02d",
                                                    habit.reminderHour,
                                                    habit.reminderMinute
                                                )
                                                Text(
                                                    text = formattedTime,
                                                    fontSize = 11.sp,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                                )
                                            }
                                        }
                                    }
                                }
                                
                                Spacer(modifier = Modifier.width(12.dp))
                                
                                // Beautiful circular checklist button (active/checked state matches image)
                                val isSelectedToday = selectedDate == DateUtils.getTodayString()
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isCompleted) {
                                                if (isSelectedToday) habitColor else habitColor.copy(alpha = 0.5f)
                                            } else Color.Transparent
                                        )
                                        .border(
                                            width = 1.5.dp,
                                            color = if (isCompleted) {
                                                Color.Transparent
                                            } else {
                                                if (isSelectedToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                                            },
                                            shape = CircleShape
                                        )
                                        .clickable(enabled = isSelectedToday) {
                                            viewModel.toggleHabitLog(
                                                habit.id,
                                                selectedDate,
                                                !isCompleted
                                            )
                                        }
                                        .testTag("habit_checkbox_${habit.id}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isCompleted) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Completed",
                                            tint = Color.White,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    } else if (!isSelectedToday) {
                                        Text("🔒", fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                    }

                    // Japanese Slogan / Quote block centered beautifully at bottom of the list
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "“",
                                    fontSize = 42.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Black,
                                    modifier = Modifier.height(30.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = selectedMantra.second,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = selectedMantra.first,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }
            }
        }
        
        // Add Habit Dialog
        if (showAddDialog) {
            HabitDialog(
                onDismiss = { showAddDialog = false },
                onSave = { name, desc, color, icon, rHour, rMin ->
                    viewModel.addHabit(name, desc, color, icon, rHour, rMin)
                    showAddDialog = false
                }
            )
        }
        
        // Edit / Delete Habit Dialog
        editingHabit?.let { habit ->
            HabitDialog(
                habit = habit,
                onDismiss = { editingHabit = null },
                onSave = { name, desc, color, icon, rHour, rMin ->
                    viewModel.updateHabit(habit.copy(
                        name = name,
                        description = desc,
                        colorHex = color,
                        iconName = icon,
                        reminderHour = rHour,
                        reminderMinute = rMin
                    ))
                    editingHabit = null
                },
                onDelete = {
                    viewModel.deleteHabit(habit)
                    editingHabit = null
                }
            )
        }

        // Display Complete Streak Details Dialog on tap of Habit card!
        viewingStreakHabit?.let { habit ->
            val habitLogs = logs.filter { it.habitId == habit.id }
            val stats = remember(habitLogs) {
                StreakCalculator.calculate(habitLogs, habit.createdAt)
            }
            val habitColor = remember(habit.colorHex) {
                try {
                    Color(android.graphics.Color.parseColor(habit.colorHex))
                } catch (e: Exception) {
                    Color(0xFF10B981)
                }
            }

            Dialog(onDismissRequest = { viewingStreakHabit = null }) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .testTag("streak_details_dialog"),
                    shape = RoundedCornerShape(24.dp),
                    tonalElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title / Emoji Header
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(habitColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            val iconParts = habit.iconName.split(" ")
                            val emoji = if (iconParts.isNotEmpty()) iconParts[0] else "⭐"
                            Text(text = emoji, fontSize = 32.sp)
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = habit.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        if (habit.description.isNotEmpty()) {
                            Text(
                                text = habit.description,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Aggregate Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = habitColor.copy(alpha = 0.12f)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Current Streak", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = habitColor, maxLines = 1)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${stats.currentStreak}d", fontSize = 16.sp, fontWeight = FontWeight.Black, color = habitColor)
                                }
                            }

                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("High Streak", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${stats.maxStreak}d", fontSize = 16.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }

                            Card(
                                modifier = Modifier.weight(1f),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Rate", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("${stats.completionRate.toInt()}%", fontSize = 16.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Streak Heatmap representation of last 18 weeks
                        StreakHeatmap(
                            habit = habit,
                            logs = habitLogs,
                            onCellToggle = { dateStr, state ->
                                viewModel.toggleHabitLog(habit.id, dateStr, state)
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Creator profile attribution explicitly styled as per request
                        Text(
                            text = "Tsuzuku • Developed by Animesh Gupta",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = {
                                    // Trigger editing instead!
                                    editingHabit = habit
                                    viewingStreakHabit = null
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Edit Settings")
                            }

                            Button(
                                onClick = { viewingStreakHabit = null },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = habitColor)
                            ) {
                                Text("Done", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitDialog(
    habit: Habit? = null,
    onDismiss: () -> Unit,
    onSave: (String, String, String, String, Int?, Int?) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf(habit?.name ?: "") }
    var desc by remember { mutableStateOf(habit?.description ?: "") }
    var selectedColor by remember { mutableStateOf(habit?.colorHex ?: "#10B981") }
    var selectedIcon by remember { mutableStateOf(habit?.iconName ?: "🏋️ Workout") }
    
    var enableReminder by remember { mutableStateOf(habit?.reminderHour != null) }
    var reminderHour by remember { mutableStateOf(habit?.reminderHour ?: 12) }
    var reminderMinute by remember { mutableStateOf(habit?.reminderMinute ?: 0) }

    val colors = listOf(
        "#10B981", // Emerald Green
        "#EF4444", // Coral Red
        "#06B6D4", // Ocean Blue
        "#6366F1", // Indigo
        "#F97316", // Sunset Orange
        "#8B5CF6"  // Purple Twilight
    )
    
    val icons = listOf(
        "🏋️ Workout",
        "💧 Hydrate",
        "📚 Read",
        "🧘 Meditate",
        "🍎 Nutrition",
        "🎯 Focus",
        "💻 Code",
        "✨ Other"
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .testTag("habit_dialog_surface"),
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 6.dp
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (habit == null) "Create Streak" else "Edit Habit Settings",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        if (habit != null && onDelete != null) {
                            IconButton(
                                onClick = onDelete,
                                modifier = Modifier.testTag("delete_habit_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
                
                // Name
                item {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Habit Name") },
                        placeholder = { Text("e.g., Daily Gym, Drink water") },
                        modifier = Modifier.fillMaxWidth().testTag("dialog_input_name"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                
                // Desc
                item {
                    OutlinedTextField(
                        value = desc,
                        onValueChange = { desc = it },
                        label = { Text("Short Description") },
                        placeholder = { Text("e.g., 30 mins cardiovascular training") },
                        modifier = Modifier.fillMaxWidth().testTag("dialog_input_desc"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                }
                
                // Category icon selection
                item {
                    Text(
                        text = "Category Icon",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(icons) { iconItem ->
                            val isSelected = selectedIcon == iconItem
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedIcon = iconItem },
                                label = { Text(iconItem) },
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier.testTag("chip_icon_$iconItem")
                            )
                        }
                    }
                }
                
                // Color Select
                item {
                    Text(
                        text = "Theme Accent Color",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        colors.forEach { colString ->
                            val isSelected = selectedColor == colString
                            val uiColor = Color(android.graphics.Color.parseColor(colString))
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(uiColor)
                                    .border(
                                        width = if (isSelected) 3.dp else 0.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clickable { selectedColor = colString }
                                    .testTag("color_select_$colString"),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = if (colString == "#FFFFFF") Color.Black else Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                
                // Daily Push Reminder Scheduler
                item {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Daily Habits Reminder Alert",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Push notification on device",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        Switch(
                            checked = enableReminder,
                            onCheckedChange = { enableReminder = it },
                            modifier = Modifier.testTag("switch_reminder")
                        )
                    }
                }
                
                if (enableReminder) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Set Reminder Time",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Simple Time spinners/numbers
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Hour (0-23)", style = MaterialTheme.typography.labelSmall)
                                    Slider(
                                        value = reminderHour.toFloat(),
                                        onValueChange = { reminderHour = it.toInt() },
                                        valueRange = 0f..23f,
                                        steps = 22,
                                        modifier = Modifier.testTag("slider_hour")
                                    )
                                    Text(
                                        text = "$reminderHour h",
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Minute (0-59)", style = MaterialTheme.typography.labelSmall)
                                    Slider(
                                        value = reminderMinute.toFloat(),
                                        onValueChange = { reminderMinute = it.toInt() },
                                        valueRange = 0f..59f,
                                        steps = 58,
                                        modifier = Modifier.testTag("slider_minute")
                                    )
                                    Text(
                                        text = String.format("%02d m", reminderMinute),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(4.dp))
                            val formattedAlert = String.format("Alert will trigger daily at %02d:%02d local clock.", reminderHour, reminderMinute)
                            Text(
                                text = formattedAlert,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                
                // Action Buttons
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onDismiss, modifier = Modifier.testTag("dialog_cancel_button")) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (name.trim().isNotEmpty()) {
                                    onSave(
                                        name.trim(),
                                        desc.trim(),
                                        selectedColor,
                                        selectedIcon,
                                        if (enableReminder) reminderHour else null,
                                        if (enableReminder) reminderMinute else null
                                    )
                                }
                            },
                            enabled = name.trim().isNotEmpty(),
                            modifier = Modifier.testTag("dialog_save_button")
                        ) {
                            Text(if (habit == null) "Create" else "Save Changes")
                        }
                    }
                }
            }
        }
    }
}
