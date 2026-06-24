package com.agupta07505.tsuzuku.ui.screens

import android.app.NotificationManager
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyModeScreen(
    modifier: Modifier = Modifier,
    openSetupOnLaunch: Boolean = false,
    focusViewModel: FocusViewModel = viewModel()
) {
    val state by focusViewModel.uiState.collectAsState()
    var showSetup by rememberSaveable { mutableStateOf(openSetupOnLaunch) }
    val result = state.runtime.lastResult

    LaunchedEffect(state.runtime.active) {
        if (state.runtime.active) showSetup = false
    }

    when {
        state.runtime.active -> ActiveFocusScreen(state.runtime, focusViewModel::endSession, modifier)
        result != null -> FocusResultScreen(result, focusViewModel::dismissResult, modifier)
        else -> FocusDashboard(state, onStart = { showSetup = true }, modifier)
    }

    if (showSetup && !state.runtime.active) {
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
private fun FocusDashboard(state: FocusUiState, onStart: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Tsuzuku Focus", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(
                "Stay focused. Keep going.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("TODAY'S FOCUS TIME", style = MaterialTheme.typography.labelMedium)
                    Text(formatMinutes(state.todayFocusMinutes), fontSize = 36.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FocusMetric("Total Focus", formatMinutes(state.totalFocusMinutes), Modifier.weight(1f))
                FocusMetric("Success Rate", "${state.successRate}%", Modifier.weight(1f))
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FocusMetric("Completed", state.completedSessions.toString(), Modifier.weight(1f))
                FocusMetric("Failed", state.failedSessions.toString(), Modifier.weight(1f))
            }
        }
        item {
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().height(58.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(Modifier.size(8.dp))
                Text("Start Focus Session", fontWeight = FontWeight.Bold)
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.History, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.size(8.dp))
                Text("Recent Sessions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }
        }
        if (state.recentSessions.isEmpty()) {
            item {
                Text(
                    "Your completed and failed focus sessions will appear here.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        } else {
            items(state.recentSessions, key = { it.id }) { session -> RecentSessionCard(session) }
        }
    }
}

@Composable
private fun FocusMetric(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(Modifier.padding(16.dp)) {
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun RecentSessionCard(session: FocusSession) {
    val date = remember(session.startTime) {
        SimpleDateFormat("d MMM, h:mm a", Locale.getDefault()).format(Date(session.startTime))
    }
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier.size(42.dp).background(
                    if (session.completed) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer,
                    CircleShape
                ), contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (session.completed) Icons.Default.CheckCircle else Icons.Default.Close,
                    contentDescription = null,
                    tint = if (session.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }
            Column(Modifier.padding(start = 12.dp).weight(1f)) {
                Text(session.sessionName, fontWeight = FontWeight.SemiBold)
                Text("$date • ${session.actualDurationMinutes} min", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(if (session.completed) "Completed" else "Failed", color = if (session.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelMedium)
        }
    }
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
        if (notificationManager.isNotificationPolicyAccessGranted) {
            onStart(name, duration, selectedMistakes)
        } else showDndDialog = true
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Start Focus Session", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Choose your goal, then place your phone face-down.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            item {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it.take(60) },
                    label = { Text("Session Name") },
                    placeholder = { Text("DSA Practice, Math Revision, Reading") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text("Duration", fontWeight = FontWeight.SemiBold)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf(15, 30, 45, 60, 90, -1)) { option ->
                        FilterChip(
                            selected = selectedDuration == option,
                            onClick = { selectedDuration = option },
                            label = { Text(if (option == -1) "Custom" else "$option min") }
                        )
                    }
                }
                if (selectedDuration == -1) {
                    OutlinedTextField(
                        value = customDuration,
                        onValueChange = { customDuration = it.filter(Char::isDigit).take(3) },
                        label = { Text("Minutes") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                Text("Allowed Mistakes", fontWeight = FontWeight.SemiBold)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(listOf(0, 1, 2, 3, 5, FocusSessionManager.UNLIMITED_MISTAKES)) { option ->
                        FilterChip(
                            selected = selectedMistakes == option,
                            onClick = { selectedMistakes = option },
                            label = { Text(if (option == FocusSessionManager.UNLIMITED_MISTAKES) "Unlimited" else option.toString()) }
                        )
                    }
                }
            }
            item {
                Button(
                    onClick = start,
                    enabled = name.isNotBlank() && duration in 1..720,
                    modifier = Modifier.fillMaxWidth().height(54.dp)
                ) { Text("Start Focus", fontWeight = FontWeight.Bold) }
            }
        }
    }

    if (showDndDialog) {
        AlertDialog(
            onDismissRequest = { showDndDialog = false },
            title = { Text("Do Not Disturb access") },
            text = { Text("Tsuzuku can silence interruptions during focus sessions. You can grant access in system settings, or continue without DND. The session will still work normally.") },
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
private fun ActiveFocusScreen(runtime: FocusRuntimeState, onEnd: () -> Unit, modifier: Modifier = Modifier) {
    val view = LocalView.current
    DisposableEffect(view) {
        val previous = view.keepScreenOn
        view.keepScreenOn = true
        onDispose { view.keepScreenOn = previous }
    }
    val seconds = runtime.remainingMillis / 1_000
    val time = if (seconds >= 3_600) "%02d:%02d:%02d".format(seconds / 3_600, seconds % 3_600 / 60, seconds % 60)
    else "%02d:%02d".format(seconds / 60, seconds % 60)
    val statusColor = if (runtime.phonePosition == PhonePosition.FACE_DOWN) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Timer, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(40.dp))
        Text(runtime.sessionName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        Text(time, fontSize = 64.sp, fontWeight = FontWeight.Bold)
        Text("Remaining time", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(28.dp))
        Surface(color = statusColor.copy(alpha = .14f), shape = RoundedCornerShape(18.dp)) {
            Row(Modifier.padding(horizontal = 20.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.PhoneAndroid, contentDescription = null, tint = statusColor)
                Spacer(Modifier.size(10.dp))
                Text(
                    if (runtime.phonePosition == PhonePosition.FACE_DOWN) "Phone Face Down" else "Phone Picked Up",
                    color = statusColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        runtime.warningSeconds?.let {
            Text("Place phone face-down within $it seconds", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 12.dp))
        }
        runtime.appReturnSeconds?.let {
            Text("Return to Tsuzuku within $it seconds", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 12.dp))
        }
        Spacer(Modifier.height(24.dp))
        val allowed = if (runtime.allowedMistakes == FocusSessionManager.UNLIMITED_MISTAKES) "Unlimited" else runtime.allowedMistakes.toString()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FocusMetric("Allowed mistakes", allowed, Modifier.weight(1f))
            Spacer(Modifier.size(12.dp))
            FocusMetric("Mistakes used", runtime.mistakesUsed.toString(), Modifier.weight(1f))
        }
        Spacer(Modifier.height(32.dp))
        OutlinedButton(onClick = onEnd, colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error), modifier = Modifier.fillMaxWidth()) {
            Text("End Session")
        }
    }
}

@Composable
private fun FocusResultScreen(result: FocusSession, onDone: () -> Unit, modifier: Modifier = Modifier) {
    val score = max(0, 100 - result.mistakesUsed * 10)
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            if (result.completed) Icons.Default.CheckCircle else Icons.Default.Close,
            contentDescription = null,
            tint = if (result.completed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            modifier = Modifier.size(72.dp)
        )
        Text(if (result.completed) "Session Completed" else "Session Failed", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(result.sessionName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        if (!result.completed && result.failedReason != null) Text(result.failedReason, color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultRow("Planned duration", "${result.plannedDurationMinutes} min")
                ResultRow("Actual duration", "${result.actualDurationMinutes} min")
                ResultRow("Mistakes used", result.mistakesUsed.toString())
                HorizontalDivider()
                ResultRow("Focus score", "$score / 100", emphasized = true)
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onDone, modifier = Modifier.fillMaxWidth()) { Text("Done") }
    }
}

@Composable
private fun ResultRow(label: String, value: String, emphasized: Boolean = false) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontWeight = if (emphasized) FontWeight.Bold else FontWeight.Medium, color = if (emphasized) MaterialTheme.colorScheme.primary else Color.Unspecified)
    }
}

private fun formatMinutes(minutes: Int): String = when {
    minutes < 60 -> "$minutes min"
    minutes % 60 == 0 -> "${minutes / 60} hr"
    else -> "${minutes / 60}h ${minutes % 60}m"
}

@Preview(showBackground = true)
@Composable
private fun FocusDashboardPreview() {
    MyApplicationTheme(themePreference = "green") {
        FocusDashboard(FocusUiState(), onStart = {})
    }
}
