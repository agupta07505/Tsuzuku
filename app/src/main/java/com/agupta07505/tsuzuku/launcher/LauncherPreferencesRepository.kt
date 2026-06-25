/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.launcher

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.launcherDataStore by preferencesDataStore(name = "tsuzuku_launcher_preferences")

class LauncherPreferencesRepository(private val context: Context) {
    private val dataStore = context.launcherDataStore

    val selectedAllowedPackages: Flow<List<String>> = dataStore.data.map { preferences ->
        preferences[Keys.allowedApps].orEmpty().sorted()
    }

    val widgets: Flow<List<LauncherWidgetPreference>> = dataStore.data.map { preferences ->
        defaultLauncherWidgets().map { widget ->
            widget.copy(enabled = preferences[booleanPreferencesKey("widget_${widget.key}")] ?: widget.enabled)
        }
    }

    val focusSettings: Flow<LauncherFocusSettings> = dataStore.data.map { preferences ->
        LauncherFocusSettings(
            dndEnabled = preferences[Keys.focusDnd] ?: true,
            silentModeEnabled = preferences[Keys.focusSilent] ?: true,
            trackExits = preferences[Keys.focusTrackExits] ?: true,
            showMotivationalQuote = preferences[Keys.focusQuote] ?: true,
            hideDistractingUi = preferences[Keys.focusHideUi] ?: true,
            lockOrientation = preferences[Keys.focusLockRotation] ?: false
        )
    }

    suspend fun setAllowedPackages(packages: List<String>) {
        dataStore.edit { preferences ->
            preferences[Keys.allowedApps] = packages.take(2).toSet()
        }
    }

    suspend fun setWidgetEnabled(key: String, enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("widget_$key")] = enabled
        }
    }

    suspend fun setFocusDnd(enabled: Boolean) {
        dataStore.edit { it[Keys.focusDnd] = enabled }
    }

    suspend fun setFocusSilent(enabled: Boolean) {
        dataStore.edit { it[Keys.focusSilent] = enabled }
    }

    suspend fun setFocusTrackExits(enabled: Boolean) {
        dataStore.edit { it[Keys.focusTrackExits] = enabled }
    }

    suspend fun setFocusQuote(enabled: Boolean) {
        dataStore.edit { it[Keys.focusQuote] = enabled }
    }

    suspend fun setFocusHideUi(enabled: Boolean) {
        dataStore.edit { it[Keys.focusHideUi] = enabled }
    }

    suspend fun setFocusLockRotation(enabled: Boolean) {
        dataStore.edit { it[Keys.focusLockRotation] = enabled }
    }

    private object Keys {
        val allowedApps = stringSetPreferencesKey("allowed_apps")
        val focusDnd = booleanPreferencesKey("focus_dnd")
        val focusSilent = booleanPreferencesKey("focus_silent")
        val focusTrackExits = booleanPreferencesKey("focus_track_exits")
        val focusQuote = booleanPreferencesKey("focus_quote")
        val focusHideUi = booleanPreferencesKey("focus_hide_ui")
        val focusLockRotation = booleanPreferencesKey("focus_lock_rotation")
    }
}
