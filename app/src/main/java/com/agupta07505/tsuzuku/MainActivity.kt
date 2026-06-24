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
import androidx.compose.material.icons.filled.PlayArrow
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
import com.agupta07505.tsuzuku.ui.screens.SettingsScreen
import com.agupta07505.tsuzuku.ui.screens.StatsScreen
import com.agupta07505.tsuzuku.ui.screens.TrackerScreen
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme

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
                                selected = false,
                                onClick = { /* TODO: Open Add Habit Dialog or central action */ },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(56.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF2CB5C3)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = com.agupta07505.tsuzuku.R.drawable.ic_tsuzuku_logo),
                                            contentDescription = "Add",
                                            modifier = Modifier.size(32.dp).clip(CircleShape)
                                        )
                                    }
                                },
                                label = { }, // No label for center item
                                modifier = Modifier.testTag("tab_center")
                            )

                            NavigationBarItem(
                                selected = currentTab == "study_mode",
                                onClick = { currentTab = "study_mode" },
                                icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Study Mode") },
                                label = { Text("Study Mode") },
                                modifier = Modifier.testTag("tab_study_mode")
                            )
                            NavigationBarItem(
                                selected = currentTab == "stats",
                                onClick = { currentTab = "stats" },
                                icon = { Icon(Icons.Default.Star, contentDescription = "Stats") },
                                label = { Text("Stats") },
                                modifier = Modifier.testTag("tab_stats")
                            )
                        }
                    }
                ) { innerPadding ->
                    when (currentTab) {
                        "home", "tracker" -> TrackerScreen(
                            viewModel = viewModel,
                            onNavigateToSettings = { currentTab = "settings" },
                            modifier = Modifier.padding(innerPadding)
                        )
                        "habits" -> com.agupta07505.tsuzuku.ui.screens.HabitsScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                        "study_mode" -> com.agupta07505.tsuzuku.ui.screens.StudyModeScreen(
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
