/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.data.CountdownDateEntity
import com.agupta07505.tsuzuku.ui.CountdownDateViewModel
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import com.agupta07505.tsuzuku.util.countdownDayInfo
import com.agupta07505.tsuzuku.util.formatCountdownDate
import com.agupta07505.tsuzuku.util.startOfLocalDay
import java.util.Calendar

@Composable
fun CountdownDatesRoute(
    modifier: Modifier = Modifier,
    initialEditId: Long? = null,
    viewModel: CountdownDateViewModel = viewModel()
) {
    val countdowns by viewModel.countdowns.collectAsState()
    var editing by remember { mutableStateOf<CountdownDateEntity?>(null) }
    var showEditor by remember { mutableStateOf(false) }
    var consumedInitialEdit by remember(initialEditId) { mutableStateOf(false) }

    LaunchedEffect(initialEditId, countdowns) {
        if (!consumedInitialEdit && initialEditId != null && initialEditId > 0L) {
            countdowns.firstOrNull { it.id == initialEditId }?.let {
                editing = it
                showEditor = true
                consumedInitialEdit = true
            }
        }
    }

    if (showEditor) {
        CountdownDateEditorScreen(
            countdown = editing,
            onBack = { showEditor = false; editing = null },
            onSave = { title, date, notes, icon, color ->
                val current = editing
                if (current == null) {
                    viewModel.addCountdown(title, date, notes, icon, color) {
                        showEditor = false
                        editing = null
                    }
                } else {
                    viewModel.updateCountdown(current, title, date, notes, icon, color) {
                        showEditor = false
                        editing = null
                    }
                }
            },
            modifier = modifier
        )
    } else {
        CountdownDatesScreen(
            countdowns = countdowns,
            onAdd = { editing = null; showEditor = true },
            onEdit = { editing = it; showEditor = true },
            onDelete = viewModel::deleteCountdown,
            modifier = modifier
        )
    }
}

@Composable
fun CountdownDatesScreen(
    countdowns: List<CountdownDateEntity>,
    onAdd: () -> Unit,
    onEdit: (CountdownDateEntity) -> Unit,
    onDelete: (CountdownDateEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add countdown")
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(countdownGradient())
                .padding(padding)
                .statusBarsPadding()
                .padding(horizontal = 18.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text("Countdown Dates", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black)
                Spacer(Modifier.height(6.dp))
                Text(
                    "Track important dates and stay prepared.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (countdowns.isEmpty()) {
                item { CountdownEmptyState(onAdd = onAdd, modifier = Modifier.fillMaxWidth()) }
            } else {
                items(countdowns, key = { it.id }) { countdown ->
                    CountdownDateCard(
                        countdown = countdown,
                        onEdit = { onEdit(countdown) },
                        onDelete = { onDelete(countdown) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun CountdownDateCard(
    countdown: CountdownDateEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accent = Color(countdown.accentColor)
    val info = countdownDayInfo(countdown.targetDateMillis)
    val displayAccent = if (info.isPassed) Color(0xFFFF5252) else accent

    Card(
        modifier = modifier.border(1.dp, displayAccent.copy(alpha = 0.45f), RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f))
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(displayAccent.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(iconForCountdown(countdown.iconKey), contentDescription = null, tint = displayAccent)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(countdown.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(formatCountdownDate(countdown.targetDateMillis), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                }
                IconButton(onClick = onEdit) { Icon(Icons.Default.Edit, contentDescription = "Edit countdown") }
                IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription = "Delete countdown", tint = MaterialTheme.colorScheme.error) }
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(info.headline, color = displayAccent, fontSize = if (info.isToday) 36.sp else 46.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(10.dp))
                Text(info.label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
            }
            if (!countdown.notes.isNullOrBlank()) {
                Text(countdown.notes, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountdownDateEditorScreen(
    countdown: CountdownDateEntity?,
    onBack: () -> Unit,
    onSave: (String, Long, String?, String, Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var title by remember(countdown) { mutableStateOf(countdown?.title.orEmpty()) }
    var targetDate by remember(countdown) { mutableStateOf(countdown?.targetDateMillis ?: startOfLocalDay(System.currentTimeMillis())) }
    var notes by remember(countdown) { mutableStateOf(countdown?.notes.orEmpty()) }
    var iconKey by remember(countdown) { mutableStateOf(countdown?.iconKey ?: "calendar") }
    var accentColor by remember(countdown) { mutableStateOf(countdown?.accentColor ?: CountdownAccentColors.first().value) }
    var titleTouched by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance().apply { timeInMillis = targetDate }
    val dateDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            targetDate = Calendar.getInstance().apply {
                set(year, month, day, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(containerColor = MaterialTheme.colorScheme.background, modifier = modifier) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(countdownGradient())
                .padding(padding)
                .statusBarsPadding()
                .padding(horizontal = 18.dp),
            contentPadding = PaddingValues(top = 6.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
                    Text(if (countdown == null) "Add Countdown Date" else "Edit Countdown Date", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = {
                            titleTouched = true
                            if (title.isNotBlank()) onSave(title, targetDate, notes, iconKey, accentColor)
                        }
                    ) { Text("Save") }
                }
            }
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it.take(30); titleTouched = true },
                    label = { Text("Event Title") },
                    supportingText = { Text("${title.length}/30") },
                    isError = titleTouched && title.isBlank(),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text("Select Date", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.55f), RoundedCornerShape(14.dp))
                        .clickable { dateDialog.show() }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = Color(accentColor))
                    Spacer(Modifier.width(12.dp))
                    Text(formatCountdownDate(targetDate), fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                    IconButton(onClick = { targetDate = startOfLocalDay(System.currentTimeMillis()) }) {
                        Icon(Icons.Default.Close, contentDescription = "Reset date")
                    }
                }
            }
            item {
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it.take(100) },
                    label = { Text("Notes (Optional)") },
                    supportingText = { Text("${notes.length}/100") },
                    minLines = 3,
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text("Icon", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                CountdownIconPicker(selected = iconKey, accentColor = Color(accentColor), onSelected = { iconKey = it })
            }
            item {
                Text("Color", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                CountdownColorPicker(selected = accentColor, onSelected = { accentColor = it })
            }
            item {
                Button(
                    onClick = {
                        titleTouched = true
                        if (title.isNotBlank()) onSave(title, targetDate, notes, iconKey, accentColor)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Countdown")
                }
            }
        }
    }
}

@Composable
fun CountdownEmptyState(onAdd: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.35f), RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f))
    ) {
        Column(Modifier.padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(52.dp))
            Spacer(Modifier.height(12.dp))
            Text("Nothing to track yet", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))
            Text(
                "Add an exam, deadline, or special date to start counting down.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(14.dp))
            Button(onClick = onAdd) { Text("Add Countdown") }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountdownIconPicker(selected: String, accentColor: Color, onSelected: (String) -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        CountdownIconOptions.forEach { option ->
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(if (selected == option.key) accentColor.copy(alpha = 0.22f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f))
                    .border(1.dp, if (selected == option.key) accentColor else Color.Transparent, CircleShape)
                    .clickable { onSelected(option.key) },
                contentAlignment = Alignment.Center
            ) {
                Icon(option.icon, contentDescription = option.label, tint = if (selected == option.key) accentColor else MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CountdownColorPicker(selected: Long, onSelected: (Long) -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        CountdownAccentColors.forEach { swatch ->
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(swatch.value))
                    .border(2.dp, if (selected == swatch.value) MaterialTheme.colorScheme.onSurface else Color.Transparent, CircleShape)
                    .clickable { onSelected(swatch.value) },
                contentAlignment = Alignment.Center
            ) {
                if (selected == swatch.value) Icon(Icons.Default.Check, contentDescription = swatch.label, tint = Color.Black)
            }
        }
    }
}

@Composable
fun CountdownDaysText(countdown: CountdownDateEntity, modifier: Modifier = Modifier) {
    val info = countdownDayInfo(countdown.targetDateMillis)
    Text("${info.headline} ${info.label}", modifier = modifier, color = if (info.isPassed) Color(0xFFFF5252) else Color(countdown.accentColor), fontWeight = FontWeight.Bold)
}

data class CountdownIconOption(val key: String, val label: String, val icon: ImageVector)
data class CountdownColorOption(val label: String, val value: Long)

val CountdownIconOptions = listOf(
    CountdownIconOption("exam", "Exam", Icons.Default.School),
    CountdownIconOption("book", "Book", Icons.AutoMirrored.Filled.MenuBook),
    CountdownIconOption("target", "Target", Icons.Default.TrackChanges),
    CountdownIconOption("plane", "Plane", Icons.Default.Flight),
    CountdownIconOption("gift", "Gift", Icons.Default.CardGiftcard),
    CountdownIconOption("trophy", "Trophy", Icons.Default.EmojiEvents),
    CountdownIconOption("work", "Work", Icons.Default.Work),
    CountdownIconOption("calendar", "Calendar", Icons.Default.CalendarMonth)
)

val CountdownAccentColors = listOf(
    CountdownColorOption("Green", 0xFF8FEA3C),
    CountdownColorOption("Blue", 0xFF33A6FF),
    CountdownColorOption("Purple", 0xFFB45CFF),
    CountdownColorOption("Orange", 0xFFFFA726),
    CountdownColorOption("Red", 0xFFFF5252),
    CountdownColorOption("Teal", 0xFF19D3C5)
)

fun iconForCountdown(iconKey: String): ImageVector =
    CountdownIconOptions.firstOrNull { it.key == iconKey }?.icon ?: Icons.Default.CalendarMonth

@Composable
private fun countdownGradient(): Brush = Brush.verticalGradient(
    listOf(Color(0xFF020805), MaterialTheme.colorScheme.background, Color(0xFF07140D))
)

@Preview(showBackground = true)
@Composable
private fun CountdownDatesPreview() {
    MyApplicationTheme(themePreference = "green") {
        CountdownDatesScreen(
            countdowns = listOf(
                CountdownDateEntity(title = "JEE Advanced 2025", targetDateMillis = System.currentTimeMillis() + 128L * 86_400_000L, notes = "Stay consistent!", iconKey = "exam"),
                CountdownDateEntity(title = "Project Deadline", targetDateMillis = System.currentTimeMillis() - 21L * 86_400_000L, iconKey = "work", accentColor = 0xFFFF5252)
            ),
            onAdd = {},
            onEdit = {},
            onDelete = {}
        )
    }
}
