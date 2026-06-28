/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.launcher

import android.graphics.drawable.Drawable

const val MAX_ALLOWED_LAUNCHER_APPS = 3

data class LauncherAppInfo(
    val packageName: String,
    val label: String,
    val icon: Drawable? = null,
    val locked: Boolean = false
)

data class LauncherWidgetPreference(
    val key: String,
    val title: String,
    val subtitle: String,
    val enabled: Boolean
)

data class LauncherFocusSettings(
    val dndEnabled: Boolean = false,
    val silentModeEnabled: Boolean = true,
    val trackExits: Boolean = true,
    val showMotivationalQuote: Boolean = true,
    val hideDistractingUi: Boolean = true,
    val lockOrientation: Boolean = false
)

data class LauncherUiState(
    val isDefaultLauncher: Boolean = false,
    val selectedAllowedPackages: List<String> = emptyList(),
    val selectedAllowedApps: List<LauncherAppInfo> = emptyList(),
    val installedApps: List<LauncherAppInfo> = emptyList(),
    val widgets: List<LauncherWidgetPreference> = defaultLauncherWidgets(),
    val focusSettings: LauncherFocusSettings = LauncherFocusSettings(),
    val loadingApps: Boolean = true
) {
    val selectedCount: Int get() = selectedAllowedPackages.size
}

fun defaultLauncherWidgets(): List<LauncherWidgetPreference> = listOf(
    LauncherWidgetPreference("center_clock", "Center Clock", "Launcher clock slot", true),
    LauncherWidgetPreference("motivational_quote", "Motivational Quote", "Uses Tsuzuku's quote source", true),
    LauncherWidgetPreference("countdown_date", "Countdown Date", "Track days left for exams, deadlines, or events.", true),
    LauncherWidgetPreference("today_habits", "Today's Habits", "Uses your Tsuzuku habit data", true),
    LauncherWidgetPreference("focus_timer", "Focus Timer", "Connects to Tsuzuku Focus", true),
    LauncherWidgetPreference("streak_card", "Streak Card", "Reserved for Tsuzuku streak widget", false),
    LauncherWidgetPreference("focus_stats", "Focus Stats", "Reserved for Tsuzuku focus stats widget", false)
)
