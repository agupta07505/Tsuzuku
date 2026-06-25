/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.launcher

import android.app.Application
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agupta07505.tsuzuku.LauncherActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LauncherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = LauncherPreferencesRepository(application.applicationContext)
    private val installedApps = MutableStateFlow<List<LauncherAppInfo>>(emptyList())
    private val defaultLauncher = MutableStateFlow(isTsuzukuDefaultLauncher())
    private val loadingApps = MutableStateFlow(true)

    private val preferenceState = combine(
        repository.selectedAllowedPackages,
        repository.widgets,
        repository.focusSettings
    ) { selectedPackages, widgets, focusSettings ->
        Triple(selectedPackages, widgets, focusSettings)
    }

    private val runtimeState = combine(
        installedApps,
        defaultLauncher,
        loadingApps
    ) { apps, isDefault, loading ->
        Triple(apps, isDefault, loading)
    }

    val uiState = combine(preferenceState, runtimeState) { preferences, runtime ->
        val selectedPackages = preferences.first
        val widgets = preferences.second
        val focusSettings = preferences.third
        val apps = runtime.first
        val isDefault = runtime.second
        val loading = runtime.third
        LauncherUiState(
            isDefaultLauncher = isDefault,
            selectedAllowedPackages = selectedPackages,
            selectedAllowedApps = selectedPackages.mapNotNull { packageName ->
                apps.firstOrNull { it.packageName == packageName }
            },
            installedApps = apps,
            widgets = widgets,
            focusSettings = focusSettings,
            loadingApps = loading
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LauncherUiState())

    var canUseDndState by mutableStateOf(false)
        private set

    init {
        refreshInstalledApps()
        refreshLauncherStatus()
        refreshDndPermission()
    }

    fun refreshLauncherStatus() {
        defaultLauncher.value = isTsuzukuDefaultLauncher()
    }

    fun refreshInstalledApps() {
        viewModelScope.launch {
            loadingApps.value = true
            installedApps.value = loadLaunchableApps()
            loadingApps.value = false
        }
    }

    fun toggleAllowedApp(packageName: String): Boolean {
        val selected = uiState.value.selectedAllowedPackages
        return if (packageName in selected) {
            viewModelScope.launch { repository.setAllowedPackages(selected - packageName) }
            true
        } else if (selected.size < 3) {
            viewModelScope.launch { repository.setAllowedPackages(selected + packageName) }
            true
        } else {
            false
        }
    }

    fun setWidgetEnabled(key: String, enabled: Boolean) {
        viewModelScope.launch { repository.setWidgetEnabled(key, enabled) }
    }

    fun setFocusDnd(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusDnd(enabled) }
    }

    fun setFocusSilent(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusSilent(enabled) }
    }

    fun setFocusTrackExits(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusTrackExits(enabled) }
    }

    fun setFocusQuote(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusQuote(enabled) }
    }

    fun setFocusHideUi(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusHideUi(enabled) }
    }

    fun setFocusLockRotation(enabled: Boolean) {
        viewModelScope.launch { repository.setFocusLockRotation(enabled) }
    }

    fun refreshDndPermission() {
        canUseDndState = canUseDnd()
    }

    fun canUseDnd(): Boolean {
        val notificationManager = getApplication<Application>()
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || notificationManager.isNotificationPolicyAccessGranted
    }

    fun openDefaultLauncherSettings(context: Context) {
        openFirstAvailable(
            context,
            listOf(
                Intent(Settings.ACTION_HOME_SETTINGS),
                Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS),
                Intent(Settings.ACTION_SETTINGS)
            )
        )
    }

    fun openDndPolicySettings(context: Context) {
        openFirstAvailable(
            context,
            listOf(
                Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS),
                Intent(Settings.ACTION_SETTINGS)
            )
        )
    }

    fun openApp(context: Context, packageName: String): Boolean {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        return if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            true
        } else {
            false
        }
    }

    fun openPhone(context: Context): Boolean {
        val intent = Intent(Intent.ACTION_DIAL).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return runCatching {
            context.startActivity(intent)
            true
        }.getOrDefault(false)
    }

    fun phoneApp(): LauncherAppInfo = LauncherAppInfo(
        packageName = "android.intent.action.DIAL",
        label = "Phone",
        icon = null,
        locked = true
    )

    private suspend fun loadLaunchableApps(): List<LauncherAppInfo> = withContext(Dispatchers.Default) {
        val context = getApplication<Application>().applicationContext
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
            .asSequence()
            .filter { it.activityInfo.packageName != context.packageName }
            .mapNotNull { resolveInfo ->
                val packageName = resolveInfo.activityInfo.packageName
                runCatching {
                    LauncherAppInfo(
                        packageName = packageName,
                        label = resolveInfo.loadLabel(packageManager).toString(),
                        icon = resolveInfo.loadIcon(packageManager)
                    )
                }.getOrNull()
            }
            .distinctBy { it.packageName }
            .sortedBy { it.label.lowercase() }
            .toList()
    }

    private fun openFirstAvailable(context: Context, intents: List<Intent>) {
        val packageManager = context.packageManager
        val flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val launchIntent = intents.firstOrNull { it.resolveActivity(packageManager) != null } ?: intents.last()
        runCatching { context.startActivity(launchIntent.addFlags(flags)) }
    }

    private fun isTsuzukuDefaultLauncher(): Boolean {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
        val resolveInfo = context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val componentName = resolveInfo?.activityInfo?.let {
            ComponentName(it.packageName, it.name)
        }
        val launcherComponent = ComponentName(context, LauncherActivity::class.java)
        return componentName == launcherComponent
    }

    fun applyLauncherFocusModeIfAllowed() {
        val settings = uiState.value.focusSettings
        val context = getApplication<Application>().applicationContext
        if (settings.dndEnabled && canUseDnd()) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            runCatching { notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY) }
        }
        if (settings.silentModeEnabled) {
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            runCatching { audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT }
        }
    }
}
