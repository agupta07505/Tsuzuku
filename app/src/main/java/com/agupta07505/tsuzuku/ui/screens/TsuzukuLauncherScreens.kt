/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.screens

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.FormatQuote
import androidx.compose.material.icons.outlined.NotificationsOff
import androidx.compose.material.icons.outlined.ScreenRotation
import androidx.compose.material.icons.outlined.VolumeOff
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.agupta07505.tsuzuku.data.FocusSession
import com.agupta07505.tsuzuku.data.Habit
import com.agupta07505.tsuzuku.data.HabitLog
import com.agupta07505.tsuzuku.data.Quotes
import com.agupta07505.tsuzuku.launcher.LauncherAppInfo
import com.agupta07505.tsuzuku.launcher.LauncherFocusSettings
import com.agupta07505.tsuzuku.launcher.LauncherUiState
import com.agupta07505.tsuzuku.launcher.LauncherWidgetPreference
import com.agupta07505.tsuzuku.launcher.defaultLauncherWidgets
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import com.agupta07505.tsuzuku.util.DateUtils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed interface LauncherRoute {
    data object Settings : LauncherRoute
    data object Activation : LauncherRoute
    data object Preview : LauncherRoute
    data object Widgets : LauncherRoute
    data object Focus : LauncherRoute
}

private val LauncherBackground = Color(0xFF041008)
private val LauncherCard = Color(0xFF0B1A12)
private val LauncherGreen = Color(0xFF7EE35F)
private val LauncherMuted = Color(0xFFB8C8BC)

@Composable
fun TsuzukuLauncherSettingsScreen(
    uiState: LauncherUiState,
    onNavigate: (LauncherRoute) -> Unit,
    onToggleAllowedApp: (String) -> Boolean,
    onOpenLauncherSettings: (Context) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = LauncherBackground,
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherGradient())
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Tsuzuku Launcher", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Turn your phone into a minimal focus launcher.",
                            color = LauncherMuted,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    TsuzukuGlowLogo()
                }
            }

            item {
                LauncherStatusCard(
                    isActive = uiState.isDefaultLauncher,
                    onClick = { onNavigate(LauncherRoute.Activation) }
                )
            }

            item {
                Text("Quick Actions", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    LauncherActionCard(
                        icon = Icons.Default.RocketLaunch,
                        iconTint = LauncherGreen,
                        title = "Activate Tsuzuku Launcher",
                        subtitle = "Set as default launcher",
                        onClick = { onNavigate(LauncherRoute.Activation) }
                    )
                    LauncherActionCard(
                        icon = Icons.Default.Apps,
                        iconTint = Color(0xFF38BDF8),
                        title = "Allowed Apps",
                        subtitle = "Choose only 2 apps",
                        onClick = {}
                    )
                    AllowedAppsSelector(
                        uiState = uiState,
                        onToggleAllowedApp = { packageName ->
                            if (!onToggleAllowedApp(packageName)) {
                                scope.launch { snackbarHostState.showSnackbar("Only 2 apps can be selected.") }
                            }
                        }
                    )
                    LauncherActionCard(
                        icon = Icons.Default.Widgets,
                        iconTint = Color(0xFFFACC15),
                        title = "Widgets",
                        subtitle = "Choose Tsuzuku widgets",
                        onClick = { onNavigate(LauncherRoute.Widgets) }
                    )
                    LauncherActionCard(
                        icon = Icons.Default.Visibility,
                        iconTint = Color(0xFFC084FC),
                        title = "Launcher Preview",
                        subtitle = "See how it looks",
                        onClick = { onNavigate(LauncherRoute.Preview) }
                    )
                    LauncherActionCard(
                        icon = Icons.Default.Security,
                        iconTint = LauncherGreen,
                        title = "Focus Mode",
                        subtitle = "Configure focus environment",
                        onClick = { onNavigate(LauncherRoute.Focus) }
                    )
                }
            }

            item {
                Button(
                    onClick = { onOpenLauncherSettings(context) },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LauncherGreen, contentColor = Color(0xFF061007))
                ) {
                    Icon(Icons.Default.RocketLaunch, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text("Activate Launcher", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun LauncherStatusCard(
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PremiumCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Launcher Status", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text(
                        if (isActive) "Active" else "Inactive",
                        color = if (isActive) LauncherGreen else Color(0xFFFF6B5F),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    if (isActive) "Tsuzuku Launcher is active." else "Tsuzuku Launcher is not active.",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    if (isActive) "Pressing Home opens your focus launcher." else "Your current default launcher is your system launcher.",
                    color = LauncherMuted,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = LauncherMuted)
        }
    }
}

@Composable
fun LauncherActionCard(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PremiumCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        contentPadding = PaddingValues(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(iconTint.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint)
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = LauncherMuted)
        }
    }
}

@Composable
fun AllowedAppsSelector(
    uiState: LauncherUiState,
    onToggleAllowedApp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    PremiumCard(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Allowed Apps", fontWeight = FontWeight.Bold)
                Text("${uiState.selectedCount}/2 Selected besides Phone", color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
            }
            TextButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Hide" else "Select")
            }
        }
        Spacer(Modifier.height(12.dp))
        LauncherAllowedAppRow(app = LauncherAppInfo("phone", "Phone (Caller)", locked = true), selected = true, locked = true)
        uiState.selectedAllowedApps.forEach { app ->
            LauncherAllowedAppRow(app = app, selected = true, onClick = { onToggleAllowedApp(app.packageName) })
        }
        AnimatedVisibility(expanded) {
            Column(Modifier.padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (uiState.loadingApps) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                uiState.installedApps.take(60).forEach { app ->
                    LauncherAllowedAppRow(
                        app = app,
                        selected = app.packageName in uiState.selectedAllowedPackages,
                        onClick = { onToggleAllowedApp(app.packageName) }
                    )
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Text("Only selected apps will be accessible when launcher is active.", color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun LauncherAllowedAppRow(
    app: LauncherAppInfo,
    selected: Boolean,
    locked: Boolean = app.locked,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = !locked, onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LauncherAppIcon(icon = app.icon, fallback = if (locked) Icons.Default.Phone else Icons.Default.Apps)
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(app.label, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(if (locked) "Always allowed" else if (selected) "Selected" else "Tap to allow", color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
        }
        if (locked) {
            Icon(Icons.Default.Lock, contentDescription = null, tint = LauncherGreen)
        } else {
            FilterChip(
                selected = selected,
                onClick = onClick,
                label = { Text(if (selected) "Selected" else "Select") }
            )
        }
    }
}

@Composable
fun LauncherActivationInstructionsScreen(
    isActive: Boolean,
    onBack: () -> Unit,
    onOpenSettings: (Context) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LauncherPageScaffold(title = "Activate Tsuzuku Launcher", onBack = onBack, modifier = modifier) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(LauncherGreen.copy(alpha = 0.13f))
                        .border(1.dp, LauncherGreen.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = LauncherGreen, modifier = Modifier.size(62.dp))
                }
                Spacer(Modifier.height(26.dp))
                Text(
                    if (isActive) "Tsuzuku is your default launcher" else "Set Tsuzuku as your default launcher",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "To activate Tsuzuku Launcher, you need to set it as your default Home app.",
                    color = LauncherMuted,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                ActivationStep(1, "Tap the button below", "It will open your system settings")
                ActivationStep(2, "Choose \"Tsuzuku\"", "Select Tsuzuku Launcher from list")
                ActivationStep(3, "Set as default", "Choose \"Always\"")
                ActivationStep(4, "You're all set!", "Return here and start focusing")
            }
        }

        item {
            Button(
                onClick = { onOpenSettings(context) },
                modifier = Modifier.fillMaxWidth().height(58.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LauncherGreen, contentColor = Color(0xFF061007)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Settings, contentDescription = null)
                Spacer(Modifier.width(10.dp))
                Text("Open Default Launcher Settings", fontWeight = FontWeight.Bold)
            }
            TextButton(onClick = { onOpenSettings(context) }, modifier = Modifier.fillMaxWidth()) {
                Text("How it works?")
            }
        }
    }
}

@Composable
private fun ActivationStep(number: Int, title: String, subtitle: String) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(1.dp, LauncherGreen, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(number.toString(), color = LauncherGreen, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = LauncherMuted, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ManageLauncherWidgetsScreen(
    widgets: List<LauncherWidgetPreference>,
    onBack: () -> Unit,
    onToggleWidget: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LauncherPageScaffold(
        title = "Manage Widgets",
        onBack = onBack,
        action = { TextButton(onClick = { defaultLauncherWidgets().forEach { onToggleWidget(it.key, it.enabled) } }) { Text("Reset") } },
        modifier = modifier
    ) {
        item {
            Text("Choose and arrange widgets to show on launcher home.", color = LauncherMuted)
        }
        item {
            PremiumCard(contentPadding = PaddingValues(0.dp)) {
                widgets.forEach { widget ->
                    LauncherWidgetToggleRow(widget = widget, onToggle = { onToggleWidget(widget.key, it) })
                    if (widget != widgets.last()) HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f))
                }
            }
        }
    }
}

@Composable
fun LauncherWidgetToggleRow(
    widget: LauncherWidgetPreference,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.GridView, contentDescription = null, tint = LauncherGreen)
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(widget.title, fontWeight = FontWeight.Bold)
            Text(widget.subtitle, color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
        }
        Switch(checked = widget.enabled, onCheckedChange = onToggle)
    }
}

@Composable
fun LauncherFocusSettingsScreen(
    focusSettings: LauncherFocusSettings,
    canUseDnd: Boolean,
    onBack: () -> Unit,
    onOpenDndSettings: (Context) -> Unit,
    onDndChange: (Boolean) -> Unit,
    onSilentChange: (Boolean) -> Unit,
    onTrackExitsChange: (Boolean) -> Unit,
    onQuoteChange: (Boolean) -> Unit,
    onHideUiChange: (Boolean) -> Unit,
    onLockRotationChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LauncherPageScaffold(
        title = "Focus Mode",
        onBack = onBack,
        action = { TextButton(onClick = onBack) { Text("Save") } },
        modifier = modifier
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Shield, contentDescription = null, tint = LauncherGreen, modifier = Modifier.size(96.dp))
                Spacer(Modifier.height(12.dp))
                Text("Focus Mode", color = LauncherGreen, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Lock distractions. Stay in flow.", color = LauncherMuted)
            }
        }
        item {
            PremiumCard(contentPadding = PaddingValues(0.dp)) {
                FocusSettingRow(Icons.Outlined.NotificationsOff, "Do Not Disturb", "Block notifications", focusSettings.dndEnabled, onDndChange)
                FocusSettingRow(Icons.Outlined.VolumeOff, "Silent Mode", "Mute calls, alerts and media", focusSettings.silentModeEnabled, onSilentChange)
                FocusSettingRow(Icons.Outlined.WarningAmber, "Track Exits", "Track how many times you exit", focusSettings.trackExits, onTrackExitsChange)
                FocusSettingRow(Icons.Outlined.FormatQuote, "Motivational Quote", "Show daily motivation", focusSettings.showMotivationalQuote, onQuoteChange)
                FocusSettingRow(Icons.Default.Visibility, "Hide Distracting UI", "Keep launcher minimal", focusSettings.hideDistractingUi, onHideUiChange)
                FocusSettingRow(Icons.Outlined.ScreenRotation, "Lock Device Rotation", "Prevent accidental rotation", focusSettings.lockOrientation, onLockRotationChange)
            }
        }
        if (focusSettings.dndEnabled && !canUseDnd) {
            item {
                PremiumCard {
                    Text("DND permission is not granted", color = Color(0xFFFFC857), fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text("Tsuzuku can continue without DND, or you can allow Notification Policy Access in settings.", color = LauncherMuted)
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(onClick = { onOpenDndSettings(context) }) {
                        Text("Open DND Permission Settings")
                    }
                }
            }
        }
        item {
            PremiumCard {
                Text("Focus Mode will be automatically enabled when launcher is active.", color = LauncherMuted)
            }
        }
    }
}

@Composable
private fun FocusSettingRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = LauncherGreen)
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun LauncherPreviewScreen(
    uiState: LauncherUiState,
    habits: List<Habit>,
    logs: List<HabitLog>,
    focusSessions: List<FocusSession>,
    onBack: () -> Unit,
    onOpenApp: (Context, String) -> Boolean,
    onOpenPhone: (Context) -> Boolean,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    TsuzukuLauncherHomeScreen(
        uiState = uiState,
        habits = habits,
        logs = logs,
        focusSessions = focusSessions,
        previewMode = true,
        onBack = onBack,
        onOpenApp = onOpenApp,
        onOpenPhone = onOpenPhone,
        onOpenSettings = onOpenSettings,
        modifier = modifier
    )
}

@Composable
fun TsuzukuLauncherHomeScreen(
    uiState: LauncherUiState,
    habits: List<Habit>,
    logs: List<HabitLog>,
    focusSessions: List<FocusSession>,
    previewMode: Boolean,
    onBack: (() -> Unit)? = null,
    onOpenApp: (Context, String) -> Boolean,
    onOpenPhone: (Context) -> Boolean,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val quote = remember { Quotes.byIndex((System.currentTimeMillis() / 86_400_000L).toInt()) }
    val today = remember { DateUtils.getTodayString() }
    val completedToday = logs.count { it.date == today && it.isCompleted }
    val activeHabits = habits.filterNot { it.isArchived }
    val todayFocusMinutes = focusSessions
        .filter { it.completed && SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it.startTime)) == today }
        .sumOf { it.actualDurationMinutes }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = LauncherBackground,
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherHomeGradient())
                .padding(padding)
                .padding(horizontal = 18.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (previewMode && onBack != null) {
                        IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                    }
                    Icon(Icons.Default.Spa, contentDescription = null, tint = LauncherGreen)
                    Spacer(Modifier.width(8.dp))
                    Text("Tsuzuku Launcher", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    AssistChip(
                        onClick = {},
                        label = { Text(if (uiState.isDefaultLauncher) "Active" else "Preview") },
                        leadingIcon = { Icon(Icons.Default.Security, contentDescription = null, modifier = Modifier.size(16.dp)) }
                    )
                }
            }

            item {
                LauncherClockWidget()
            }

            if (uiState.widgets.firstOrNull { it.key == "motivational_quote" }?.enabled == true && uiState.focusSettings.showMotivationalQuote) {
                item {
                    LauncherQuoteWidget(japanese = quote.japanese, english = quote.english)
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LauncherAppButton(label = "Phone", icon = null, fallback = Icons.Default.Phone) {
                        if (!onOpenPhone(context)) scope.launch { snackbarHostState.showSnackbar("Phone app is unavailable.") }
                    }
                    uiState.selectedAllowedApps.take(2).forEach { app ->
                        LauncherAppButton(label = app.label, icon = app.icon, fallback = Icons.Default.Apps) {
                            if (!onOpenApp(context, app.packageName)) scope.launch { snackbarHostState.showSnackbar("${app.label} is unavailable.") }
                        }
                    }
                    repeat((2 - uiState.selectedAllowedApps.size).coerceAtLeast(0)) {
                        LauncherAppButton(label = "Select App", icon = null, fallback = Icons.Default.Apps) {
                            scope.launch { snackbarHostState.showSnackbar("Choose apps from Tsuzuku Launcher settings.") }
                        }
                    }
                }
            }

            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    if (uiState.widgets.firstOrNull { it.key == "today_habits" }?.enabled == true) {
                        TodayHabitsLauncherWidget(
                            completed = completedToday,
                            total = activeHabits.size,
                            habits = activeHabits,
                            logs = logs,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (uiState.widgets.firstOrNull { it.key == "focus_timer" }?.enabled == true) {
                        LauncherFocusTimerWidget(todayFocusMinutes = todayFocusMinutes, modifier = Modifier.weight(1f))
                    }
                }
            }

            if (uiState.widgets.firstOrNull { it.key == "streak_card" }?.enabled == true || uiState.widgets.firstOrNull { it.key == "focus_stats" }?.enabled == true) {
                item {
                    PremiumCard(modifier = Modifier.fillMaxWidth()) {
                        Text("Consistency dashboard", color = LauncherGreen, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(6.dp))
                        Text("${activeHabits.size} active habits • ${todayFocusMinutes}m focused today", color = LauncherMuted)
                    }
                }
            }

            item {
                Text("Swipe up for all widgets", color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { scope.launch { snackbarHostState.showSnackbar("Only selected apps are available in Tsuzuku Launcher.") } }) {
                        Icon(Icons.Default.GridView, contentDescription = "Apps", tint = LauncherGreen)
                    }
                    TsuzukuGlowLogo(size = 66.dp)
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = LauncherGreen)
                    }
                }
            }
        }
    }
}

@Composable
fun LauncherClockWidget(modifier: Modifier = Modifier) {
    var now by remember { mutableStateOf(System.currentTimeMillis()) }
    LaunchedEffect(Unit) {
        while (true) {
            now = System.currentTimeMillis()
            kotlinx.coroutines.delay(1000)
        }
    }
    val time = remember(now) { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(now)) }
    Text(
        text = time,
        fontSize = 72.sp,
        lineHeight = 78.sp,
        fontWeight = FontWeight.Light,
        modifier = modifier.padding(top = 22.dp)
    )
}

@Composable
fun LauncherQuoteWidget(japanese: String, english: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(japanese, color = LauncherGreen, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(6.dp))
        Text(english, color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
    }
}

@Composable
fun LauncherFocusTimerWidget(todayFocusMinutes: Int, modifier: Modifier = Modifier) {
    PremiumCard(modifier = modifier.height(186.dp)) {
        Text("Focus Timer", color = LauncherGreen, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(progress = { 0.72f }, strokeWidth = 8.dp, color = LauncherGreen, modifier = Modifier.size(92.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("25:00", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("${todayFocusMinutes}m today", color = LauncherMuted, style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(Modifier.weight(1f))
        Text("Tap Focus tab to start", color = LauncherMuted, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun TodayHabitsLauncherWidget(
    completed: Int,
    total: Int,
    habits: List<Habit>,
    logs: List<HabitLog>,
    modifier: Modifier = Modifier
) {
    val today = DateUtils.getTodayString()
    PremiumCard(modifier = modifier.height(186.dp)) {
        Text("Today's Habits", color = LauncherGreen, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { if (total == 0) 0f else completed.toFloat() / total.toFloat() },
                    color = LauncherGreen,
                    strokeWidth = 7.dp,
                    modifier = Modifier.size(74.dp)
                )
                Text("$completed/$total", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Completed", style = MaterialTheme.typography.bodySmall)
                habits.take(5).forEach { habit ->
                    val done = logs.any { it.habitId == habit.id && it.date == today && it.isCompleted }
                    Text(
                        "${if (done) "●" else "○"} ${habit.name}",
                        color = if (done) LauncherGreen else LauncherMuted,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun LauncherAppButton(
    label: String,
    icon: Drawable?,
    fallback: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.width(88.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(LauncherGreen.copy(alpha = 0.20f)),
            contentAlignment = Alignment.Center
        ) {
            LauncherAppIcon(icon = icon, fallback = fallback, size = 64.dp, fallbackSize = 34.dp)
        }
        Spacer(Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, maxLines = 1, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
    }
}

@Composable
fun LauncherAppIcon(
    icon: Drawable?,
    fallback: ImageVector,
    size: androidx.compose.ui.unit.Dp = 40.dp,
    fallbackSize: androidx.compose.ui.unit.Dp = 24.dp
) {
    if (icon != null) {
        val bitmap = remember(icon) { icon.toBitmap(width = 96, height = 96).asImageBitmap() }
        Image(
            bitmap = bitmap,
            contentDescription = null,
            modifier = Modifier.size(size).clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit
        )
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(12.dp))
                .background(LauncherGreen.copy(alpha = 0.16f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(fallback, contentDescription = null, tint = LauncherGreen, modifier = Modifier.size(fallbackSize))
        }
    }
}

@Composable
private fun LauncherPageScaffold(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    action: @Composable RowScope.() -> Unit = {},
    content: LazyListScope.() -> Unit
) {
    Scaffold(containerColor = LauncherBackground, modifier = modifier) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherGradient())
                .padding(padding)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                    Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                    action()
                }
            }
            content()
        }
    }
}

@Composable
private fun PremiumCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(18.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.28f), RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = LauncherCard.copy(alpha = 0.88f))
    ) {
        Column(modifier = Modifier.padding(contentPadding), content = content)
    }
}

@Composable
private fun TsuzukuGlowLogo(modifier: Modifier = Modifier, size: androidx.compose.ui.unit.Dp = 70.dp) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(18.dp, CircleShape, ambientColor = LauncherGreen, spotColor = LauncherGreen)
            .clip(CircleShape)
            .background(LauncherGreen.copy(alpha = 0.10f))
            .border(1.dp, LauncherGreen.copy(alpha = 0.55f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Spa, contentDescription = "Tsuzuku", tint = LauncherGreen, modifier = Modifier.size(size * 0.58f))
    }
}

private fun launcherGradient(): Brush = Brush.verticalGradient(
    colors = listOf(LauncherBackground, Color(0xFF06170D), LauncherBackground)
)

private fun launcherHomeGradient(): Brush = Brush.verticalGradient(
    colors = listOf(Color(0xFF031008), Color(0xFF0E2E18), Color(0xFF041008))
)

@Preview(showBackground = true)
@Composable
private fun TsuzukuLauncherSettingsPreview() {
    MyApplicationTheme(themePreference = "green") {
        TsuzukuLauncherSettingsScreen(
            uiState = LauncherUiState(installedApps = listOf(LauncherAppInfo("notes", "Keep Notes")), selectedAllowedPackages = listOf("notes")),
            onNavigate = {},
            onToggleAllowedApp = { true },
            onOpenLauncherSettings = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TsuzukuLauncherHomePreview() {
    MyApplicationTheme(themePreference = "green") {
        TsuzukuLauncherHomeScreen(
            uiState = LauncherUiState(
                isDefaultLauncher = true,
                selectedAllowedApps = listOf(LauncherAppInfo("notes", "Keep Notes"), LauncherAppInfo("calc", "Calculator")),
                widgets = defaultLauncherWidgets()
            ),
            habits = listOf(Habit(id = 1, name = "Code daily"), Habit(id = 2, name = "Read 20 pages")),
            logs = listOf(HabitLog(habitId = 1, date = DateUtils.getTodayString(), isCompleted = true)),
            focusSessions = emptyList(),
            previewMode = false,
            onOpenApp = { _, _ -> true },
            onOpenPhone = { true },
            onOpenSettings = {}
        )
    }
}
