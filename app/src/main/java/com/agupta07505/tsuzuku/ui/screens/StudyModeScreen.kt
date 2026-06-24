package com.agupta07505.tsuzuku.ui.screens

import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.StayCurrentPortrait
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.data.FocusSession
import com.agupta07505.tsuzuku.focus.FocusRuntimeState
import com.agupta07505.tsuzuku.focus.FocusSessionManager
import com.agupta07505.tsuzuku.focus.PhonePosition
import com.agupta07505.tsuzuku.ui.FocusUiState
import com.agupta07505.tsuzuku.ui.FocusViewModel
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.max

private val FocusGreen = Color(0xFF65DF5B)
private val FocusOrange = Color(0xFFFF762F)
private val FocusPurple = Color(0xFF9B72F2)
private val FocusRed = Color(0xFFFF554D)
private val FocusCard = Color(0xFF07150E)
private val FocusOutline = Color(0xFF193B27)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyModeScreen(
    modifier: Modifier = Modifier,
    openSetupOnLaunch: Boolean = false,
    focusViewModel: FocusViewModel = viewModel()
) {
    val state by focusViewModel.uiState.collectAsState()
    var showSetup by rememberSaveable { mutableStateOf(openSetupOnLaunch) }
    val runtime = state.runtime

    LaunchedEffect(runtime.active) {
        if (runtime.active) showSetup = false
    }

    FocusBackground(modifier) {
        when {
            runtime.active && runtime.appReturnSeconds != null -> ReturnToFocusScreen(runtime)
            runtime.active && runtime.warningSeconds != null -> FocusWarningScreen(runtime)
            runtime.active -> ActiveFocusScreen(runtime, focusViewModel::endSession)
            runtime.lastResult != null -> FocusResultScreen(runtime.lastResult, focusViewModel::dismissResult)
            else -> FocusDashboard(state, onStart = { showSetup = true })
        }
    }

    if (showSetup && !runtime.active) {
        FocusSetupSheet(
            onDismiss = { showSetup = false },
            onStart = { name, minutes, allowed ->
                focusViewModel.startSession(name, minutes, allowed)
                showSetup = false
            }
        )
    }
}

@Composable
private fun FocusBackground(modifier: Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(MaterialTheme.colorScheme.background, Color(0xFF031109), MaterialTheme.colorScheme.background)
                )
            )
    ) { content() }
}

@Composable
private fun FocusDashboard(state: FocusUiState, onStart: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Tsuzuku Focus", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("Stay focused. Keep going.", color = FocusGreen, style = MaterialTheme.typography.bodyMedium)
                }
                Surface(shape = CircleShape, color = FocusGreen.copy(alpha = .12f)) {
                    Icon(Icons.Default.BarChart, null, tint = FocusGreen, modifier = Modifier.padding(11.dp).size(22.dp))
                }
            }
        }
        item { FocusMetricsBoard(state) }
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Recent Sessions", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("View all", color = FocusGreen, style = MaterialTheme.typography.labelMedium)
            }
        }
        if (state.recentSessions.isEmpty()) {
            item {
                OutlinedFocusCard {
                    Text(
                        "Your focus history will appear here after your first session.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(18.dp)
                    )
                }
            }
        } else {
            items(state.recentSessions.take(3), key = { it.id }) { RecentSessionCard(it) }
        }
        item {
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FocusGreen, contentColor = Color(0xFF041007))
            ) {
                Icon(Icons.Default.PlayArrow, null)
                Spacer(Modifier.size(8.dp))
                Text("Start Focus Session", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun FocusMetricsBoard(state: FocusUiState) {
    OutlinedFocusCard {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
            MetricRow(
                left = Triple("Today's Focus", formatMinutes(state.todayFocusMinutes), Icons.Default.Schedule),
                right = Triple("Total Focus Time", formatMinutes(state.totalFocusMinutes), Icons.Default.AccessTime)
            )
            HorizontalDivider(color = FocusOutline, modifier = Modifier.padding(vertical = 8.dp))
            MetricRow(
                left = Triple("Completed Sessions", state.completedSessions.toString(), Icons.Default.CheckCircle),
                right = Triple("Failed Sessions", state.failedSessions.toString(), Icons.Default.Close)
            )
            HorizontalDivider(color = FocusOutline, modifier = Modifier.padding(vertical = 8.dp))
            MetricRow(
                left = Triple("Success Rate", "${state.successRate}%", Icons.Default.TrendingUp),
                right = Triple("Current Streak", "${state.currentStreakDays} days", Icons.Default.LocalFireDepartment),
                rightColor = FocusOrange
            )
        }
    }
}

@Composable
private fun MetricRow(
    left: Triple<String, String, androidx.compose.ui.graphics.vector.ImageVector>,
    right: Triple<String, String, androidx.compose.ui.graphics.vector.ImageVector>,
    rightColor: Color = FocusGreen
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        FocusMetric(left.first, left.second, left.third, FocusGreen, Modifier.weight(1f))
        VerticalDivider(color = FocusOutline, modifier = Modifier.height(62.dp))
        FocusMetric(right.first, right.second, right.third, rightColor, Modifier.weight(1f).padding(start = 14.dp))
    }
}

@Composable
private fun FocusMetric(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(vertical = 5.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = color, modifier = Modifier.size(18.dp))
            Spacer(Modifier.size(7.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 25.dp, top = 3.dp))
    }
}

@Composable
private fun RecentSessionCard(session: FocusSession) {
    val date = remember(session.startTime) {
        SimpleDateFormat("EEE, h:mm a", Locale.getDefault()).format(Date(session.startTime))
    }
    OutlinedFocusCard {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = CircleShape, color = FocusGreen.copy(alpha = .13f)) {
                Icon(Icons.Default.MenuBook, null, tint = FocusGreen, modifier = Modifier.padding(9.dp).size(20.dp))
            }
            Column(Modifier.padding(start = 11.dp).weight(1f)) {
                Text(session.sessionName, fontWeight = FontWeight.SemiBold)
                Text(
                    "${session.actualDurationMinutes} min  •  ${session.mistakesUsed} ${if (session.mistakesUsed == 1) "mistake" else "mistakes"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(date, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            StatusPill(session.completed)
        }
    }
}

@Composable
private fun StatusPill(completed: Boolean) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = (if (completed) FocusGreen else FocusRed).copy(alpha = .12f)
    ) {
        Text(
            if (completed) "Completed" else "Failed",
            color = if (completed) FocusGreen else FocusRed,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun OutlinedFocusCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, FocusOutline),
        colors = CardDefaults.cardColors(containerColor = FocusCard.copy(alpha = .82f))
    ) { content() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FocusSetupSheet(onDismiss: () -> Unit, onStart: (String, Int, Int) -> Unit) {
    val context = LocalContext.current
    val notificationManager = remember { context.getSystemService(NotificationManager::class.java) }
    var name by rememberSaveable { mutableStateOf("") }
    var selectedDuration by rememberSaveable { mutableIntStateOf(30) }
    var customDuration by rememberSaveable { mutableStateOf("") }
    var selectedMistakes by rememberSaveable { mutableIntStateOf(0) }
    var showDndDialog by remember { mutableStateOf(false) }
    val duration = if (selectedDuration == -1) customDuration.toIntOrNull() ?: 0 else selectedDuration
    val start = {
        if (notificationManager.isNotificationPolicyAccessGranted) onStart(name, duration, selectedMistakes)
        else showDndDialog = true
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF0C1715),
        dragHandle = {
            Box(Modifier.padding(10.dp).size(width = 42.dp, height = 4.dp).clip(CircleShape).background(Color(0xFF67736E)))
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { Text("Start Focus Session", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) }
            item {
                Text("Session Name", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 7.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it.take(30) },
                    placeholder = { Text("Math Revision") },
                    leadingIcon = { Icon(Icons.Default.MenuBook, null, tint = FocusGreen) },
                    supportingText = { Text("${name.length}/30", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = FocusGreen),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text("Duration", fontWeight = FontWeight.SemiBold)
                SessionChipRow(listOf(15, 30, 45, 60), selectedDuration) { selectedDuration = it }
                SessionChipRow(listOf(90, -1), selectedDuration) { selectedDuration = it }
                if (selectedDuration == -1) {
                    OutlinedTextField(
                        value = customDuration,
                        onValueChange = { customDuration = it.filter(Char::isDigit).take(3) },
                        label = { Text("Custom minutes") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = FocusGreen),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                Text("Allowed Mistakes", fontWeight = FontWeight.SemiBold)
                SessionChipRow(listOf(0, 1, 2, 3, 5), selectedMistakes) { selectedMistakes = it }
                SessionChipRow(listOf(FocusSessionManager.UNLIMITED_MISTAKES), selectedMistakes) { selectedMistakes = it }
            }
            item {
                OutlinedFocusCard {
                    Column(Modifier.padding(13.dp), verticalArrangement = Arrangement.spacedBy(11.dp)) {
                        InfoRow(Icons.Default.NotificationsNone, "You will be alerted if you pick up the phone.")
                        InfoRow(Icons.Default.PhoneAndroid, "Keep your phone face down during the session.")
                    }
                }
            }
            item {
                Button(
                    onClick = start,
                    enabled = name.isNotBlank() && duration in 1..720,
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FocusGreen, contentColor = Color(0xFF041007)),
                    shape = RoundedCornerShape(11.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, null)
                    Spacer(Modifier.size(8.dp))
                    Text("Start Focus", fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    if (showDndDialog) {
        AlertDialog(
            onDismissRequest = { showDndDialog = false },
            title = { Text("Do Not Disturb access") },
            text = { Text("Tsuzuku can silence interruptions during focus sessions. Grant access in system settings, or continue without DND.") },
            confirmButton = {
                TextButton(onClick = {
                    showDndDialog = false
                    context.startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
                }) { Text("Open Settings") }
            },
            dismissButton = {
                TextButton(onClick = { showDndDialog = false; onStart(name, duration, selectedMistakes) }) {
                    Text("Continue without DND")
                }
            }
        )
    }
}

@Composable
private fun SessionChipRow(options: List<Int>, selected: Int, onSelected: (Int) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        options.forEach { option ->
            FilterChip(
                selected = selected == option,
                onClick = { onSelected(option) },
                label = {
                    Text(
                        when (option) {
                            -1 -> "Custom"
                            FocusSessionManager.UNLIMITED_MISTAKES -> "∞ Unlimited"
                            else -> if (options.any { it > 5 && it != FocusSessionManager.UNLIMITED_MISTAKES }) "$option min" else option.toString()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = FocusGreen,
                    selectedLabelColor = Color(0xFF061108),
                    containerColor = Color.Transparent
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selected == option,
                    borderColor = Color(0xFF405049),
                    selectedBorderColor = FocusGreen
                ),
                modifier = if (options.size > 1) Modifier.weight(1f) else Modifier
            )
        }
    }
}

@Composable
private fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = FocusPurple, modifier = Modifier.size(19.dp))
        Spacer(Modifier.size(10.dp))
        Text(text, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun ActiveFocusScreen(runtime: FocusRuntimeState, onEnd: () -> Unit) {
    KeepScreenOn()
    val totalMillis = runtime.plannedDurationMinutes.coerceAtLeast(1) * 60_000L
    val progress = (runtime.remainingMillis.toFloat() / totalMillis).coerceIn(0f, 1f)
    val statusDown = runtime.phonePosition == PhonePosition.FACE_DOWN
    val statusColor = if (statusDown) FocusGreen else FocusRed

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = CircleShape, color = FocusGreen.copy(alpha = .12f), border = BorderStroke(1.dp, FocusOutline)) {
                    Icon(Icons.Default.MenuBook, null, tint = FocusGreen, modifier = Modifier.padding(12.dp).size(28.dp))
                }
                Column(Modifier.padding(start = 13.dp).weight(1f)) {
                    Text(runtime.sessionName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("${runtime.plannedDurationMinutes} min Focus Session", color = FocusGreen)
                }
                IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, "Session options") }
            }
        }
        item {
            CircularValue(progress, FocusGreen, 254.dp) {
                Text(formatClock(runtime.remainingMillis), fontSize = 48.sp, fontWeight = FontWeight.Bold)
                Text("Remaining", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SessionValueCard("Allowed Mistakes", formatAllowed(runtime.allowedMistakes), FocusGreen, Modifier.weight(1f))
                SessionValueCard("Mistakes Used", runtime.mistakesUsed.toString(), FocusRed, Modifier.weight(1f))
            }
        }
        item {
            OutlinedFocusCard {
                Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = statusColor.copy(alpha = .13f)) {
                        Icon(
                            if (statusDown) Icons.Default.CloseFullscreen else Icons.Default.StayCurrentPortrait,
                            null,
                            tint = statusColor,
                            modifier = Modifier.padding(10.dp).size(24.dp)
                        )
                    }
                    Column(Modifier.padding(start = 12.dp)) {
                        Text("Current Status", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(if (statusDown) "Phone Face Down" else "Phone Picked Up", color = statusColor, fontWeight = FontWeight.Bold)
                        Text(if (statusDown) "Great! Keep it down and stay focused." else "Place it face down to stay focused.", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
        item { Spacer(Modifier.height(8.dp)) }
        item {
            OutlinedButton(
                onClick = onEnd,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                border = BorderStroke(1.dp, FocusRed),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = FocusRed),
                shape = RoundedCornerShape(11.dp)
            ) {
                Icon(Icons.Default.Close, null)
                Spacer(Modifier.size(8.dp))
                Text("End Session")
            }
        }
    }
}

@Composable
private fun SessionValueCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, FocusOutline),
        colors = CardDefaults.cardColors(containerColor = FocusCard)
    ) {
        Column(Modifier.fillMaxWidth().padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = if (color == FocusRed) color else MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = color)
        }
    }
}

@Composable
private fun FocusWarningScreen(runtime: FocusRuntimeState) {
    KeepScreenOn()
    val seconds = runtime.warningSeconds ?: 5
    val remaining = if (runtime.allowedMistakes == FocusSessionManager.UNLIMITED_MISTAKES) "∞"
    else (runtime.allowedMistakes - runtime.mistakesUsed).coerceAtLeast(0).toString()
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.WarningAmber, null, tint = FocusOrange, modifier = Modifier.size(92.dp))
            Text("Focus Warning", color = FocusOrange, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("Keep your phone face down.", modifier = Modifier.padding(top = 13.dp))
            Text("You have picked up your phone.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 9.dp))
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, FocusOrange),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(Modifier.fillMaxWidth().padding(17.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Mistakes Remaining", color = FocusOrange, fontWeight = FontWeight.SemiBold)
                Text("$remaining / ${formatAllowed(runtime.allowedMistakes)}", color = FocusOrange, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            }
        }
        CircularValue(seconds / 5f, FocusOrange, 154.dp) {
            Text(seconds.toString(), fontSize = 44.sp, fontWeight = FontWeight.Bold)
            Text("seconds", style = MaterialTheme.typography.bodySmall)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ExitToApp, null, tint = FocusOrange, modifier = Modifier.size(34.dp))
            Spacer(Modifier.size(12.dp))
            Text("Put your phone down", color = FocusOrange, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun ReturnToFocusScreen(runtime: FocusRuntimeState) {
    val seconds = runtime.appReturnSeconds ?: 5
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(shape = CircleShape, color = FocusPurple.copy(alpha = .12f), border = BorderStroke(3.dp, FocusPurple.copy(alpha = .35f))) {
                Icon(Icons.Default.ExitToApp, null, tint = FocusPurple, modifier = Modifier.padding(20.dp).size(48.dp))
            }
            Text("Return to Focus Session", color = FocusPurple, fontSize = 23.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 25.dp))
            Text("You left the app.", modifier = Modifier.padding(top = 16.dp))
            Text("Return within 5 seconds", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 9.dp))
            Text("to continue your session.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 9.dp))
        }
        CircularValue(seconds / 5f, FocusPurple, 154.dp) {
            Text(seconds.toString(), fontSize = 46.sp, fontWeight = FontWeight.Bold)
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, FocusPurple.copy(alpha = .45f)),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Session: ${runtime.sessionName}")
                Text("Remaining Time: ${formatClock(runtime.remainingMillis)}", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun CircularValue(progress: Float, color: Color, size: androidx.compose.ui.unit.Dp, content: @Composable () -> Unit) {
    Box(Modifier.size(size), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize().padding(8.dp)) {
            val width = 8.dp.toPx()
            drawCircle(color.copy(alpha = .22f), style = Stroke(width))
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * progress.coerceIn(0f, 1f),
                useCenter = false,
                style = Stroke(width = width, cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) { content() }
    }
}

@Composable
private fun FocusResultScreen(result: FocusSession, onDone: () -> Unit) {
    val score = max(0, 100 - result.mistakesUsed * 10)
    val accent = if (result.completed) FocusGreen else FocusRed
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Box(Modifier.padding(top = 24.dp), contentAlignment = Alignment.Center) {
                Canvas(Modifier.size(108.dp)) { drawCircle(accent.copy(alpha = .16f)); drawCircle(accent, style = Stroke(4.dp.toPx())) }
                Icon(if (result.completed) Icons.Default.Check else Icons.Default.Close, null, tint = accent, modifier = Modifier.size(58.dp))
            }
        }
        item {
            Text(
                if (result.completed) "Focus Session Completed!" else "Focus Session Failed",
                color = accent,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                if (result.completed) "Great work! You stayed focused." else result.failedReason ?: "Keep going. Your next session is a fresh start.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        item {
            OutlinedFocusCard {
                Column(Modifier.padding(horizontal = 17.dp)) {
                    ResultRow("Session Name", result.sessionName, Icons.Default.MenuBook)
                    HorizontalDivider(color = FocusOutline)
                    ResultRow("Planned Duration", "${result.plannedDurationMinutes} min")
                    HorizontalDivider(color = FocusOutline)
                    ResultRow("Actual Duration", "${result.actualDurationMinutes} min")
                    HorizontalDivider(color = FocusOutline)
                    ResultRow("Mistakes Used", result.mistakesUsed.toString())
                    HorizontalDivider(color = FocusOutline)
                    ResultRow("Focus Score", "$score / 100", valueColor = accent)
                }
            }
        }
        item {
            Button(
                onClick = onDone,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FocusGreen, contentColor = Color(0xFF041007)),
                shape = RoundedCornerShape(11.dp)
            ) { Text("Done", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
private fun ResultRow(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(Modifier.fillMaxWidth().padding(vertical = 17.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.weight(1f))
        if (icon != null) {
            Icon(icon, null, tint = FocusGreen, modifier = Modifier.size(18.dp))
            Spacer(Modifier.size(7.dp))
        }
        Text(value, color = valueColor, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun KeepScreenOn() {
    val view = LocalView.current
    DisposableEffect(view) {
        val previous = view.keepScreenOn
        view.keepScreenOn = true
        onDispose { view.keepScreenOn = previous }
    }
}

private fun formatAllowed(allowed: Int): String =
    if (allowed == FocusSessionManager.UNLIMITED_MISTAKES) "Unlimited" else allowed.toString()

private fun formatClock(millis: Long): String {
    val seconds = millis.coerceAtLeast(0) / 1_000
    return if (seconds >= 3_600) "%02d:%02d:%02d".format(seconds / 3_600, seconds % 3_600 / 60, seconds % 60)
    else "%02d:%02d".format(seconds / 60, seconds % 60)
}

private fun formatMinutes(minutes: Int): String = when {
    minutes < 60 -> "$minutes min"
    minutes % 60 == 0 -> "${minutes / 60}h"
    else -> "${minutes / 60}h ${minutes % 60}m"
}

@Preview(showBackground = true)
@Composable
private fun FocusDashboardPreview() {
    MyApplicationTheme(themePreference = "green") { FocusDashboard(FocusUiState(), onStart = {}) }
}
