/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.ui.HabitViewModel
import com.agupta07505.tsuzuku.launcher.LauncherViewModel
import com.agupta07505.tsuzuku.ui.screens.LauncherActivationInstructionsScreen
import com.agupta07505.tsuzuku.ui.screens.LauncherFocusSettingsScreen
import com.agupta07505.tsuzuku.ui.screens.LauncherPreviewScreen
import com.agupta07505.tsuzuku.ui.screens.LauncherRoute
import com.agupta07505.tsuzuku.ui.screens.ManageLauncherWidgetsScreen
import com.agupta07505.tsuzuku.ui.screens.SettingsScreen
import com.agupta07505.tsuzuku.ui.screens.StatsScreen
import com.agupta07505.tsuzuku.ui.screens.TrackerScreen
import com.agupta07505.tsuzuku.ui.screens.TsuzukuLauncherSettingsScreen
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import com.agupta07505.tsuzuku.focus.FocusSessionManager
import com.agupta07505.tsuzuku.focus.FocusSessionService

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _ -> /* Notification permission callback */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Request runtime permission for local notification alerts on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        setContent {
            val sharedPref = remember { getSharedPreferences("streak_marker_prefs", Context.MODE_PRIVATE) }
            var themePreference by remember { 
                mutableStateOf(sharedPref.getString("theme", "green") ?: "green") 
            }
            var customAccentColorHex by remember {
                mutableStateOf(sharedPref.getString("custom_accent_color", "") ?: "")
            }

            MyApplicationTheme(
                themePreference = themePreference,
                customAccentColorHex = customAccentColorHex
            ) {
                val viewModel: HabitViewModel = viewModel()
                val launcherViewModel: LauncherViewModel = viewModel()
                var currentTab by remember { mutableStateOf(if (intent?.getStringExtra("open_tab") == "launcher") "launcher" else "tracker") }
                var launcherRoute by remember { mutableStateOf<LauncherRoute>(LauncherRoute.Settings) }
                var openFocusSetup by remember { mutableStateOf(false) }
                val focusRuntime by FocusSessionManager.state.collectAsState()
                val launcherUiState by launcherViewModel.uiState.collectAsState()
                val habits by viewModel.managedHabits.collectAsState()
                val logs by viewModel.allLogs.collectAsState()
                val focusSessions by viewModel.focusSessions.collectAsState()

                LaunchedEffect(focusRuntime.active) {
                    if (focusRuntime.active) currentTab = "study_mode"
                }

                LaunchedEffect(currentTab) {
                    if (currentTab == "launcher") launcherViewModel.refreshLauncherStatus()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (!focusRuntime.active && focusRuntime.lastResult == null) {
                            NavigationBar(
                                modifier = Modifier.testTag("bottom_nav_bar")
                            ) {
                            NavigationBarItem(
                                selected = currentTab == "home",
                                onClick = { currentTab = "home" },
                                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                label = { Text("Home") },
                                modifier = Modifier.testTag("tab_home")
                            )
                            NavigationBarItem(
                                selected = currentTab == "habits",
                                onClick = { currentTab = "habits" },
                                icon = { Icon(Icons.Default.List, contentDescription = "Habits") },
                                label = { Text("Habits") },
                                modifier = Modifier.testTag("tab_habits")
                            )
                            
                            // Center Leaf Logo Item
                            NavigationBarItem(
                                selected = currentTab == "launcher",
                                onClick = { launcherRoute = LauncherRoute.Settings; currentTab = "launcher" },
                                icon = {
                                    androidx.compose.foundation.Image(
                                        painter = androidx.compose.ui.res.painterResource(id = com.agupta07505.tsuzuku.R.drawable.ic_tsuzuku_create_habbit_logo),
                                        contentDescription = "Tsuzuku Launcher",
                                        modifier = Modifier
                                            .size(56.dp)
                                            .clip(CircleShape),
                                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                    )
                                },
                                label = { }, // No label for center item
                                modifier = Modifier.testTag("tab_center")
                            )

                            NavigationBarItem(
                                selected = currentTab == "study_mode",
                                onClick = { openFocusSetup = false; currentTab = "study_mode" },
                                icon = { Icon(Icons.Default.School, contentDescription = "Focus") },
                                label = { Text("Focus") },
                                modifier = Modifier.testTag("tab_study_mode")
                            )
                            NavigationBarItem(
                                selected = currentTab == "stats",
                                onClick = { currentTab = "stats" },
                                icon = { Icon(Icons.Default.BarChart, contentDescription = "Stats") },
                                label = { Text("Stats") },
                                modifier = Modifier.testTag("tab_stats")
                            )
                            }
                        }
                    }
                ) { innerPadding ->
                    when (currentTab) {
                        "home", "tracker" -> TrackerScreen(
                            viewModel = viewModel,
                            onNavigateToSettings = { currentTab = "settings" },
                            onNavigateToFocus = { openFocusSetup = true; currentTab = "study_mode" },
                            modifier = Modifier.padding(innerPadding)
                        )
                        "habits" -> com.agupta07505.tsuzuku.ui.screens.HabitsScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        "launcher" -> when (launcherRoute) {
                            LauncherRoute.Settings -> TsuzukuLauncherSettingsScreen(
                                uiState = launcherUiState,
                                onNavigate = { launcherRoute = it },
                                onToggleAllowedApp = launcherViewModel::toggleAllowedApp,
                                onOpenLauncherSettings = launcherViewModel::openDefaultLauncherSettings,
                                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                            )
                            LauncherRoute.Activation -> LauncherActivationInstructionsScreen(
                                isActive = launcherUiState.isDefaultLauncher,
                                onBack = { launcherRoute = LauncherRoute.Settings },
                                onOpenSettings = launcherViewModel::openDefaultLauncherSettings,
                                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                            )
                            LauncherRoute.Preview -> LauncherPreviewScreen(
                                uiState = launcherUiState,
                                habits = habits,
                                logs = logs,
                                focusSessions = focusSessions,
                                onBack = { launcherRoute = LauncherRoute.Settings },
                                onOpenApp = launcherViewModel::openApp,
                                onOpenPhone = launcherViewModel::openPhone,
                                onOpenSettings = { launcherRoute = LauncherRoute.Settings },
                                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                            )
                            LauncherRoute.Widgets -> ManageLauncherWidgetsScreen(
                                widgets = launcherUiState.widgets,
                                onBack = { launcherRoute = LauncherRoute.Settings },
                                onToggleWidget = launcherViewModel::setWidgetEnabled,
                                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                            )
                            LauncherRoute.Focus -> LauncherFocusSettingsScreen(
                                focusSettings = launcherUiState.focusSettings,
                                canUseDnd = launcherViewModel.canUseDnd(),
                                onBack = { launcherRoute = LauncherRoute.Settings },
                                onOpenDndSettings = launcherViewModel::openDndPolicySettings,
                                onDndChange = launcherViewModel::setFocusDnd,
                                onSilentChange = launcherViewModel::setFocusSilent,
                                onTrackExitsChange = launcherViewModel::setFocusTrackExits,
                                onQuoteChange = launcherViewModel::setFocusQuote,
                                onHideUiChange = launcherViewModel::setFocusHideUi,
                                onLockRotationChange = launcherViewModel::setFocusLockRotation,
                                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                            )
                        }
                        "study_mode" -> com.agupta07505.tsuzuku.ui.screens.StudyModeScreen(
                            openSetupOnLaunch = openFocusSetup,
                            modifier = Modifier.padding(innerPadding)
                        )
                        "stats" -> StatsScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        "settings" -> SettingsScreen(
                            viewModel = viewModel,
                            themeState = themePreference,
                            onThemeChanged = { newTheme ->
                                themePreference = newTheme
                                sharedPref.edit().putString("theme", newTheme).apply()
                            },
                            customAccentColorHex = customAccentColorHex,
                            onAccentColorChanged = { newColorHex ->
                                customAccentColorHex = newColorHex
                                sharedPref.edit().putString("custom_accent_color", newColorHex).apply()
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (FocusSessionManager.state.value.active) {
            FocusSessionService.send(this, FocusSessionService.ACTION_APP_FOREGROUND)
        }
    }

    override fun onStop() {
        if (FocusSessionManager.state.value.active && !isChangingConfigurations) {
            FocusSessionService.send(this, FocusSessionService.ACTION_APP_BACKGROUND)
        }
        super.onStop()
    }
}
