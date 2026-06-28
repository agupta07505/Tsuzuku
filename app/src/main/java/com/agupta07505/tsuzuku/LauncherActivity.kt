/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.launcher.LauncherViewModel
import com.agupta07505.tsuzuku.ui.CountdownDateViewModel
import com.agupta07505.tsuzuku.ui.HabitViewModel
import com.agupta07505.tsuzuku.ui.screens.TsuzukuLauncherHomeScreen
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sharedPref = rememberThemePreferences()
            val themePreference = sharedPref.getString("theme", "green") ?: "green"
            val customAccentColorHex = sharedPref.getString("custom_accent_color", "") ?: ""

            MyApplicationTheme(
                themePreference = themePreference,
                customAccentColorHex = customAccentColorHex
            ) {
                val habitViewModel: HabitViewModel = viewModel()
                val countdownViewModel: CountdownDateViewModel = viewModel()
                val launcherViewModel: LauncherViewModel = viewModel()
                val launcherUiState by launcherViewModel.uiState.collectAsState()
                val habits by habitViewModel.managedHabits.collectAsState()
                val logs by habitViewModel.allLogs.collectAsState()
                val focusSessions by habitViewModel.focusSessions.collectAsState()
                val countdowns by countdownViewModel.countdowns.collectAsState()

                LaunchedEffect(Unit) {
                    launcherViewModel.refreshLauncherStatus()
                    launcherViewModel.applyLauncherFocusModeIfAllowed()
                }

                TsuzukuLauncherHomeScreen(
                    uiState = launcherUiState,
                    habits = habits,
                    logs = logs,
                    focusSessions = focusSessions,
                    countdowns = countdowns,
                    previewMode = false,
                    onOpenApp = launcherViewModel::openApp,
                    onOpenPhone = launcherViewModel::openPhone,
                    onOpenSettings = {
                        startActivity(Intent(this@LauncherActivity, MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            putExtra("open_tab", "launcher")
                        })
                    }
                )
            }
        }
    }

    private fun rememberThemePreferences() = getSharedPreferences("streak_marker_prefs", Context.MODE_PRIVATE)
}
