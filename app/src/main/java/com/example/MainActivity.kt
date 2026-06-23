package com.example

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.HabitViewModel
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.StatsScreen
import com.example.ui.screens.TrackerScreen
import com.example.ui.theme.MyApplicationTheme

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
                var currentTab by remember { mutableStateOf("tracker") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.testTag("bottom_nav_bar")
                        ) {
                            NavigationBarItem(
                                selected = currentTab == "tracker",
                                onClick = { currentTab = "tracker" },
                                icon = { Icon(Icons.Default.Check, contentDescription = "Tracker") },
                                label = { Text("Tracker") },
                                modifier = Modifier.testTag("tab_tracker")
                            )
                            NavigationBarItem(
                                selected = currentTab == "stats",
                                onClick = { currentTab = "stats" },
                                icon = { Icon(Icons.Default.Star, contentDescription = "Insights") },
                                label = { Text("Insights") },
                                modifier = Modifier.testTag("tab_stats")
                            )
                            NavigationBarItem(
                                selected = currentTab == "settings",
                                onClick = { currentTab = "settings" },
                                icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                                label = { Text("Settings") },
                                modifier = Modifier.testTag("tab_settings")
                            )
                        }
                    }
                ) { innerPadding ->
                    when (currentTab) {
                        "tracker" -> TrackerScreen(
                            viewModel = viewModel,
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
}
