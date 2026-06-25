/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.screens

import android.content.Context
import android.content.Intent
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
import androidx.compose.material.icons.outlined.PhotoCamera
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
    data object AllowedApps : LauncherRoute
    data object Preview : LauncherRoute
    data object Widgets : LauncherRoute
    data object Focus : LauncherRoute
}

private val LauncherHorizontalPadding = 18.dp
private val LauncherHomeTopPadding = 6.dp
private val LauncherHomeTopSpacing = 14.dp
private val LauncherHomeBottomPadding = 0.dp
private val LauncherHomeBottomSpacing = 8.dp

@Composable
fun TsuzukuLauncherSettingsScreen(
    uiState: LauncherUiState,
    onNavigate: (LauncherRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

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
                .padding(horizontal = LauncherHorizontalPadding),
            contentPadding = PaddingValues(top = 10.dp, bottom = 160.dp),
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
                    LauncherActionCard(
                        icon = Icons.Default.Apps,
                        iconTint = Color(0xFF38BDF8),
                        title = "Allowed Apps",
                        subtitle = "${uiState.selectedCount}/3 selected besides Phone",
                        onClick = { onNavigate(LauncherRoute.AllowedApps) }
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
                    if (expanded) "${uiState.selectedCount}/3 selected besides Phone" else "Choose only 3 apps",
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
                    "Only Phone and these 3 selected apps will appear in launcher mode.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AllowedLauncherAppsScreen(
    uiState: LauncherUiState,
    onBack: () -> Unit,
    onToggleAllowedApp: (String) -> Boolean,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherGradient())
                .padding(padding)
                .statusBarsPadding()
                .padding(horizontal = LauncherHorizontalPadding),
            contentPadding = PaddingValues(top = 6.dp, bottom = 160.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                    Text(
                        "Allowed Apps",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("${uiState.selectedCount}/3") },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            item {
                PremiumCard(modifier = Modifier.fillMaxWidth()) {
                    Text("Choose apps for launcher mode", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Phone is always available. Select up to 3 more apps that can be opened from Tsuzuku Launcher.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {
                PremiumCard(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(14.dp)) {
                    LauncherAllowedAppRow(
                        app = LauncherAppInfo("phone", "Phone (Caller)", locked = true),
                        selected = true,
                        locked = true
                    )
                }
            }

            if (uiState.loadingApps) {
                item {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            item {
                Text("Installed Apps", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            }

            if (!uiState.loadingApps && uiState.installedApps.isEmpty()) {
                item {
                    PremiumCard(modifier = Modifier.fillMaxWidth()) {
                        Text("No launchable apps found", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Only apps that Android exposes as launchable can be selected.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            items(
                items = uiState.installedApps,
                key = { it.packageName }
            ) { app ->
                PremiumCard(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(14.dp)) {
                    LauncherAllowedAppRow(
                        app = app,
                        selected = app.packageName in uiState.selectedAllowedPackages,
                        onClick = {
                            if (!onToggleAllowedApp(app.packageName)) {
                                scope.launch { snackbarHostState.showSnackbar("Only 3 apps can be selected.") }
                            }
                        }
                    )
                }
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
    var showHowItWorks by remember { mutableStateOf(false) }

    if (showHowItWorks) {
        AlertDialog(
            onDismissRequest = { showHowItWorks = false },
            title = { Text("How Tsuzuku Launcher works") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Android does not allow apps to silently become your Home launcher.")
                    Text("Tsuzuku opens the system Default Home app settings, where you choose Tsuzuku yourself.")
                    Text("After activation, pressing Home opens the minimal Tsuzuku Launcher screen.")
                    Text("You can always switch back to your system launcher from Android settings.")
                }
            },
            confirmButton = {
                TextButton(onClick = { showHowItWorks = false }) {
                    Text("Got it")
                }
            }
        )
    }

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
            TextButton(onClick = { showHowItWorks = true }, modifier = Modifier.fillMaxWidth()) {
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
    MyApplicationTheme(themePreference = "green") {
        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        var quote by remember { mutableStateOf(Quotes.random()) }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets(0.dp),
            modifier = modifier
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(launcherHomeGradient())
            ) {
                LauncherScenicBackground(modifier = Modifier.fillMaxSize())

                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = LauncherHorizontalPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LauncherHomeTopSpacing)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
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
                        LauncherQuoteWidget(japanese = quote.japanese, english = quote.english, onClick = { quote = Quotes.random() })
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = LauncherHorizontalPadding)
                        .padding(bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LauncherHomeBottomSpacing)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        LauncherAppButton(label = "Phone", icon = null, fallback = Icons.Default.Phone) {
                            if (!onOpenPhone(context)) scope.launch { snackbarHostState.showSnackbar("Phone app is unavailable.") }
                        }
                        uiState.selectedAllowedApps.take(3).forEach { app ->
                            LauncherAppButton(label = app.label, icon = app.icon, fallback = Icons.Default.Apps) {
                                if (!onOpenApp(context, app.packageName)) scope.launch { snackbarHostState.showSnackbar("${app.label} is unavailable.") }
                            }
                        }
                        repeat((3 - uiState.selectedAllowedApps.size).coerceAtLeast(0)) {
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
                        IconButton(onClick = onOpenSettings) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = {
                            runCatching {
                                val cameraIntent = Intent(android.provider.MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                context.startActivity(cameraIntent)
                            }.onFailure {
                                scope.launch { snackbarHostState.showSnackbar("Camera app is unavailable.") }
                            }
                        }) {
                            Icon(Icons.Outlined.PhotoCamera, contentDescription = "Camera", tint = MaterialTheme.colorScheme.primary)
                        }
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
fun LauncherQuoteWidget(
    japanese: String,
    english: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(japanese, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(6.dp))
        Text(english, color = MaterialTheme.colorScheme.onBackground, textAlign = TextAlign.Center)
    }
}

@Composable
private fun LauncherScenicBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val horizon = h * 0.65f

        // Draw sky vertical gradient
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF030A05), // Dark green-black top
                    Color(0xFF0A2213), // Forest green middle
                    Color(0xFF1C3A23)  // Rich foliage green bottom
                ),
                startY = 0f,
                endY = horizon
            ),
            topLeft = Offset.Zero,
            size = size
        )

        // Draw sky stars (minimal glowing stars)
        val stars = listOf(
            Triple(0.08f, 0.12f, 0.7f),
            Triple(0.18f, 0.28f, 0.5f),
            Triple(0.05f, 0.40f, 0.6f),
            Triple(0.28f, 0.15f, 0.8f),
            Triple(0.38f, 0.08f, 0.4f),
            Triple(0.48f, 0.22f, 0.7f),
            Triple(0.60f, 0.10f, 0.6f),
            Triple(0.72f, 0.25f, 0.5f),
            Triple(0.82f, 0.14f, 0.8f),
            Triple(0.92f, 0.32f, 0.6f),
            Triple(0.85f, 0.42f, 0.5f),
            Triple(0.68f, 0.35f, 0.7f),
            Triple(0.95f, 0.18f, 0.8f),
            Triple(0.22f, 0.45f, 0.5f)
        )
        stars.forEach { (xRatio, yRatio, alpha) ->
            // Glowing star halo
            drawCircle(
                color = Color(0xFFE2F4C5).copy(alpha = alpha * 0.18f),
                radius = 5.dp.toPx(),
                center = Offset(w * xRatio, horizon * yRatio)
            )
            // Star core
            drawCircle(
                color = Color(0xFFE2F4C5).copy(alpha = alpha),
                radius = 1.2.dp.toPx(),
                center = Offset(w * xRatio, horizon * yRatio)
            )
        }

        // Draw Sun/Moon and Glow
        val sunX = w * 0.50f
        val sunY = horizon
        val sunRadius = w * 0.16f

        // Sexy natural blur growing radial glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFE2F4C5).copy(alpha = 0.35f),
                    Color(0xFFE2F4C5).copy(alpha = 0.14f),
                    Color(0xFFE2F4C5).copy(alpha = 0.03f),
                    Color.Transparent
                ),
                center = Offset(sunX, sunY),
                radius = sunRadius * 2.2f
            ),
            radius = sunRadius * 2.2f,
            center = Offset(sunX, sunY)
        )
        // Core
        drawCircle(
            color = Color(0xFFE2F4C5),
            radius = sunRadius,
            center = Offset(sunX, sunY)
        )

        // Draw Far Hills
        val farHills = Path().apply {
            moveTo(0f, horizon + h * 0.02f)
            quadraticTo(w * 0.25f, horizon - h * 0.02f, w * 0.50f, horizon + h * 0.01f)
            quadraticTo(w * 0.75f, horizon + h * 0.04f, w, horizon + h * 0.01f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(farHills, Color(0xFF132A1C))

        // Pine Tree Drawing Helper
        fun drawPineTree(x: Float, base: Float, height: Float, color: Color) {
            // Trunk
            val trunkWidth = height * 0.08f
            val trunkHeight = height * 0.20f
            drawRect(
                color = color,
                topLeft = Offset(x - trunkWidth / 2f, base - trunkHeight),
                size = androidx.compose.ui.geometry.Size(trunkWidth, trunkHeight)
            )

            // Leaf Layers (5 layered triangles)
            val leafBaseY = base - trunkHeight + height * 0.05f
            val leafHeight = height - trunkHeight
            val layers = 5
            for (i in 0 until layers) {
                val layerProgress = i.toFloat() / (layers - 1)
                val layerHeight = leafHeight * 0.35f
                val layerWidth = height * (0.42f - layerProgress * 0.26f)

                val layerBottom = leafBaseY - (leafHeight * 0.75f * layerProgress)
                val layerTop = layerBottom - layerHeight

                val path = Path().apply {
                    moveTo(x, layerTop)
                    lineTo(x - layerWidth / 2f, layerBottom)
                    lineTo(x + layerWidth / 2f, layerBottom)
                    close()
                }
                drawPath(path, color)
            }
        }

        // Draw Middle Hills
        val midHills = Path().apply {
            moveTo(0f, horizon + h * 0.06f)
            quadraticTo(w * 0.30f, horizon + h * 0.02f, w * 0.60f, horizon + h * 0.07f)
            quadraticTo(w * 0.85f, horizon + h * 0.09f, w, horizon + h * 0.05f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(midHills, Color(0xFF0C1D13))

        // Draw Trees on Middle Hills (blended for atmospheric perspective)
        val midTreeColor = Color(0xFF0C1D13)
        drawPineTree(w * 0.26f, horizon + h * 0.045f, h * 0.06f, midTreeColor)
        drawPineTree(w * 0.34f, horizon + h * 0.05f, h * 0.05f, midTreeColor)
        drawPineTree(w * 0.72f, horizon + h * 0.08f, h * 0.065f, midTreeColor)

        // Draw Foreground Ridge
        val nearRidge = Path().apply {
            moveTo(0f, horizon + h * 0.11f)
            quadraticTo(w * 0.40f, horizon + h * 0.15f, w * 0.70f, horizon + h * 0.12f)
            quadraticTo(w * 0.85f, horizon + h * 0.10f, w, horizon + h * 0.13f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(nearRidge, Color(0xFF060E0A))

        // Draw Trees on Foreground (Dark Silhouettes)
        val foreTreeColor = Color(0xFF040A06)
        
        // Left foreground tree group
        drawPineTree(w * 0.06f, horizon + h * 0.12f, h * 0.15f, foreTreeColor)
        drawPineTree(w * 0.12f, horizon + h * 0.128f, h * 0.11f, foreTreeColor)
        drawPineTree(w * 0.18f, horizon + h * 0.134f, h * 0.085f, foreTreeColor)

        // Right foreground tree group
        drawPineTree(w * 0.82f, horizon + h * 0.11f, h * 0.10f, foreTreeColor)
        drawPineTree(w * 0.88f, horizon + h * 0.118f, h * 0.14f, foreTreeColor)
        drawPineTree(w * 0.94f, horizon + h * 0.125f, h * 0.12f, foreTreeColor)

        // Draw Foreground gradient overlay to smoothly transition to black at bottom of screen
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color(0xFF020503).copy(alpha = 0.92f)),
                startY = horizon + h * 0.11f,
                endY = h
            ),
            topLeft = Offset(0f, horizon + h * 0.11f),
            size = androidx.compose.ui.geometry.Size(w, h - (horizon + h * 0.11f))
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
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(launcherGradient())
                .padding(padding)
                .statusBarsPadding()
                .padding(horizontal = LauncherHorizontalPadding),
            contentPadding = PaddingValues(top = 6.dp, bottom = 160.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
            onNavigate = {}
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
