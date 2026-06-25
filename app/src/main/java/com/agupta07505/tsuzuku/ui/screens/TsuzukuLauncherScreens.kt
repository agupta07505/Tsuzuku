/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.screens

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.agupta07505.tsuzuku.data.FocusSession
import com.agupta07505.tsuzuku.R
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
@Composable
fun TsuzukuLauncherSettingsScreen(
    uiState: LauncherUiState,
    onNavigate: (LauncherRoute) -> Unit,
    onToggleAllowedApp: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var allowedAppsExpanded by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherGradient())
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 112.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                    AllowedAppsSelector(
                        uiState = uiState,
                        icon = Icons.Default.Apps,
                        iconTint = Color(0xFF38BDF8),
                        expanded = allowedAppsExpanded,
                        onExpandedChange = { allowedAppsExpanded = it },
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
                        iconTint = MaterialTheme.colorScheme.primary,
                        title = "Focus Mode",
                        subtitle = "Configure focus environment",
                        onClick = { onNavigate(LauncherRoute.Focus) }
                    )
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
                        color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
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
                Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun AllowedAppsSelector(
    uiState: LauncherUiState,
    icon: ImageVector,
    iconTint: Color,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onToggleAllowedApp: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PremiumCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onExpandedChange(!expanded) },
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
                Text("Allowed Apps", fontWeight = FontWeight.Bold)
                Text(
                    if (expanded) "${uiState.selectedCount}/2 selected besides Phone" else "Choose only 2 apps",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            TextButton(onClick = { onExpandedChange(!expanded) }) {
                Text(if (expanded) "Hide" else "Select")
            }
        }
        AnimatedVisibility(expanded) {
            Column(Modifier.padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f))
                LauncherAllowedAppRow(app = LauncherAppInfo("phone", "Phone (Caller)", locked = true), selected = true, locked = true)
                uiState.selectedAllowedApps.forEach { app ->
                    LauncherAllowedAppRow(app = app, selected = true, onClick = { onToggleAllowedApp(app.packageName) })
                }
                if (uiState.loadingApps) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                uiState.installedApps
                    .filterNot { it.packageName in uiState.selectedAllowedPackages }
                    .take(50)
                    .forEach { app ->
                    LauncherAllowedAppRow(
                        app = app,
                        selected = false,
                        onClick = { onToggleAllowedApp(app.packageName) }
                    )
                }
                Text(
                    "Only Phone and these 2 selected apps will appear in launcher mode.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
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
            Text(
                if (locked) "Always allowed" else if (selected) "Selected" else "Tap to allow",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (locked) {
            Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
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
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.13f))
                        .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Home, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(62.dp))
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
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
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(number.toString(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
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
            PremiumCard(modifier = Modifier.fillMaxWidth()) {
                Text("Widgets", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Coming soon...",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Tsuzuku app widgets you add later will be managed here.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium
                )
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
        Icon(Icons.Default.GridView, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(widget.title, fontWeight = FontWeight.Bold)
            Text(widget.subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
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
                Icon(Icons.Default.Shield, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(96.dp))
                Spacer(Modifier.height(12.dp))
                Text("Focus Mode", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("Lock distractions. Stay in flow.", color = MaterialTheme.colorScheme.onSurfaceVariant)
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
                    Text("Tsuzuku can continue without DND, or you can allow Notification Policy Access in settings.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(onClick = { onOpenDndSettings(context) }) {
                        Text("Open DND Permission Settings")
                    }
                }
            }
        }
        item {
            PremiumCard {
                Text("Focus Mode will be automatically enabled when launcher is active.", color = MaterialTheme.colorScheme.onSurfaceVariant)
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
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
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

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherHomeGradient())
                .padding(padding)
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp)
        ) {
            LauncherScenicBackground(modifier = Modifier.matchParentSize())

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (previewMode && onBack != null) {
                        IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                    }
                    TsuzukuMiniLogo()
                    Spacer(Modifier.weight(1f))
                    AssistChip(
                        onClick = {},
                        label = { Text(if (uiState.isDefaultLauncher) "Active" else "Preview") },
                        leadingIcon = { Icon(Icons.Default.Security, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.45f),
                            labelColor = MaterialTheme.colorScheme.onSurface,
                            leadingIconContentColor = MaterialTheme.colorScheme.primary
                        ),
                        border = AssistChipDefaults.assistChipBorder(
                            enabled = true,
                            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)
                        )
                    )
                }

                LauncherClockWidget()

                if (uiState.widgets.firstOrNull { it.key == "motivational_quote" }?.enabled == true && uiState.focusSettings.showMotivationalQuote) {
                    LauncherQuoteWidget(japanese = quote.japanese, english = quote.english)
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { scope.launch { snackbarHostState.showSnackbar("Only selected apps are available in Tsuzuku Launcher.") } }) {
                        Icon(Icons.Default.GridView, contentDescription = "Apps", tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.primary)
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
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}

@Composable
fun LauncherQuoteWidget(japanese: String, english: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(japanese, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(6.dp))
        Text(english, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center)
    }
}

@Composable
private fun LauncherScenicBackground(modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val primaryContainer = MaterialTheme.colorScheme.primaryContainer
    val background = MaterialTheme.colorScheme.background

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val horizon = h * 0.48f

        drawCircle(
            color = primary.copy(alpha = 0.22f),
            radius = w * 0.11f,
            center = Offset(w * 0.50f, horizon + h * 0.02f)
        )

        val farMountains = Path().apply {
            moveTo(0f, horizon + h * 0.13f)
            lineTo(w * 0.16f, horizon + h * 0.03f)
            lineTo(w * 0.31f, horizon + h * 0.10f)
            lineTo(w * 0.46f, horizon - h * 0.02f)
            lineTo(w * 0.62f, horizon + h * 0.11f)
            lineTo(w * 0.78f, horizon + h * 0.00f)
            lineTo(w, horizon + h * 0.12f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(farMountains, primaryContainer.copy(alpha = 0.30f))

        val nearMountains = Path().apply {
            moveTo(0f, horizon + h * 0.18f)
            lineTo(w * 0.22f, horizon + h * 0.06f)
            lineTo(w * 0.40f, horizon + h * 0.17f)
            lineTo(w * 0.57f, horizon + h * 0.04f)
            lineTo(w * 0.76f, horizon + h * 0.18f)
            lineTo(w, horizon + h * 0.07f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(nearMountains, primary.copy(alpha = 0.13f))

        val ridge = Path().apply {
            moveTo(0f, horizon + h * 0.22f)
            lineTo(w * 0.18f, horizon + h * 0.13f)
            lineTo(w * 0.35f, horizon + h * 0.21f)
            lineTo(w * 0.50f, horizon + h * 0.15f)
            lineTo(w * 0.66f, horizon + h * 0.23f)
            lineTo(w * 0.84f, horizon + h * 0.14f)
            lineTo(w, horizon + h * 0.22f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(ridge, background.copy(alpha = 0.44f))

        fun pine(x: Float, base: Float, height: Float, alpha: Float) {
            val trunkWidth = height * 0.07f
            drawRect(
                color = Color.Black.copy(alpha = alpha),
                topLeft = Offset(x - trunkWidth / 2f, base - height * 0.18f),
                size = androidx.compose.ui.geometry.Size(trunkWidth, height * 0.18f)
            )
            repeat(4) { index ->
                val top = base - height + height * 0.18f * index
                val half = height * (0.25f - index * 0.025f)
                val treePath = Path().apply {
                    moveTo(x, top)
                    lineTo(x - half, base - height * 0.08f * index)
                    lineTo(x + half, base - height * 0.08f * index)
                    close()
                }
                drawPath(treePath, Color.Black.copy(alpha = alpha))
            }
        }

        val treeBase = horizon + h * 0.25f
        listOf(0.02f, 0.08f, 0.15f, 0.88f, 0.94f, 1.00f).forEachIndexed { index, xRatio ->
            pine(
                x = w * xRatio,
                base = treeBase + h * 0.02f * (index % 2),
                height = h * (0.13f + 0.02f * (index % 3)),
                alpha = 0.32f
            )
        }

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, background.copy(alpha = 0.76f)),
                startY = horizon + h * 0.18f,
                endY = h
            ),
            topLeft = Offset.Zero,
            size = size
        )
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
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)),
            contentAlignment = Alignment.Center
        ) {
            LauncherAppIcon(icon = icon, fallback = fallback, size = 64.dp, fallbackSize = 34.dp)
        }
        Spacer(Modifier.height(8.dp))
        Text(
            label,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
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
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(fallback, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(fallbackSize))
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
            contentPadding = PaddingValues(bottom = 112.dp),
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.88f))
    ) {
        Column(modifier = Modifier.padding(contentPadding), content = content)
    }
}

@Composable
private fun TsuzukuGlowLogo(modifier: Modifier = Modifier, size: androidx.compose.ui.unit.Dp = 70.dp) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(18.dp, CircleShape, ambientColor = MaterialTheme.colorScheme.primary, spotColor = MaterialTheme.colorScheme.primary)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.10f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.55f), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_tsuzuku_create_habbit_logo),
            contentDescription = "Tsuzuku",
            modifier = Modifier
                .size(size * 0.82f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun TsuzukuMiniLogo(modifier: Modifier = Modifier, size: androidx.compose.ui.unit.Dp = 38.dp) {
    Image(
        painter = painterResource(id = R.drawable.ic_tsuzuku_create_habbit_logo),
        contentDescription = "Tsuzuku",
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun launcherGradient(): Brush = Brush.verticalGradient(
    colors = listOf(
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.surface.copy(alpha = 0.98f),
        MaterialTheme.colorScheme.background
    )
)

@Composable
private fun launcherHomeGradient(): Brush = Brush.verticalGradient(
    colors = listOf(
        MaterialTheme.colorScheme.background,
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.36f),
        MaterialTheme.colorScheme.background
    )
)

@Preview(showBackground = true)
@Composable
private fun TsuzukuLauncherSettingsPreview() {
    MyApplicationTheme(themePreference = "green") {
        TsuzukuLauncherSettingsScreen(
            uiState = LauncherUiState(installedApps = listOf(LauncherAppInfo("notes", "Keep Notes")), selectedAllowedPackages = listOf("notes")),
            onNavigate = {},
            onToggleAllowedApp = { true }
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
