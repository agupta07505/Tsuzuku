package com.agupta07505.tsuzuku.focus

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.agupta07505.tsuzuku.MainActivity
import com.agupta07505.tsuzuku.R
import com.agupta07505.tsuzuku.data.AppDatabase
import com.agupta07505.tsuzuku.data.FocusRepository
import com.agupta07505.tsuzuku.data.FocusSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ceil

class FocusSessionService : Service(), SensorEventListener {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var notificationManager: NotificationManager
    private lateinit var audioManager: AudioManager
    private lateinit var sensorManager: SensorManager
    private lateinit var repository: FocusRepository
    private var countdownJob: Job? = null
    private var positionGraceJob: Job? = null
    private var appGraceJob: Job? = null
    private var endTimeMillis = 0L
    private var startTimeMillis = 0L
    private var finishing = false
    private var lastPosition = PhonePosition.UNKNOWN

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NotificationManager::class.java)
        audioManager = getSystemService(AudioManager::class.java)
        sensorManager = getSystemService(SensorManager::class.java)
        repository = FocusRepository(AppDatabase.getDatabase(this).focusSessionDao())
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> if (!FocusSessionManager.state.value.active) startSession(intent)
            ACTION_END -> finishSession(completed = false, failedReason = "Ended early")
            ACTION_APP_BACKGROUND -> startAppGracePeriod()
            ACTION_APP_FOREGROUND -> cancelAppGracePeriod()
        }
        return START_REDELIVER_INTENT
    }

    private fun startSession(intent: Intent) {
        val name = intent.getStringExtra(EXTRA_NAME).orEmpty().ifBlank { "Focus Session" }
        val minutes = intent.getIntExtra(EXTRA_DURATION, 15).coerceAtLeast(1)
        val allowed = intent.getIntExtra(EXTRA_ALLOWED, 0).coerceAtLeast(0)
        startTimeMillis = intent.getLongExtra(EXTRA_START_TIME, System.currentTimeMillis())
        endTimeMillis = startTimeMillis + minutes * 60_000L
        finishing = false

        saveAndApplyAudioState()
        FocusSessionManager.update {
            FocusRuntimeState(
                active = true,
                sessionName = name,
                plannedDurationMinutes = minutes,
                allowedMistakes = allowed,
                remainingMillis = (endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0)
            )
        }
        startAsForeground()
        registerPositionSensor()
        countdownJob = scope.launch {
            while (FocusSessionManager.state.value.active) {
                val remaining = (endTimeMillis - System.currentTimeMillis()).coerceAtLeast(0)
                FocusSessionManager.update { it.copy(remainingMillis = remaining) }
                updateNotification()
                if (remaining == 0L) {
                    finishSession(completed = true, failedReason = null)
                    break
                }
                delay(1_000)
            }
        }
    }

    private fun registerPositionSensor() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
            ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (!FocusSessionManager.state.value.active) return
        val position = if (event.values[2] < -7f) PhonePosition.FACE_DOWN else PhonePosition.PICKED_UP
        if (position == lastPosition) return
        lastPosition = position
        FocusSessionManager.update { it.copy(phonePosition = position) }
        if (position == PhonePosition.FACE_DOWN) cancelPositionGracePeriod() else startPositionGracePeriod()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    private fun startPositionGracePeriod() {
        if (positionGraceJob?.isActive == true) return
        warnUser()
        positionGraceJob = scope.launch {
            for (second in 5 downTo 1) {
                FocusSessionManager.update { it.copy(warningSeconds = second) }
                delay(1_000)
                if (lastPosition == PhonePosition.FACE_DOWN) return@launch
            }
            FocusSessionManager.update { current ->
                current.copy(
                    mistakesUsed = current.mistakesUsed + 1,
                    // Keep the warning screen latched until the sensor reports FACE_DOWN.
                    warningSeconds = 0
                )
            }
            val state = FocusSessionManager.state.value
            if (state.mistakesUsed > state.allowedMistakes) {
                finishSession(completed = false, failedReason = "Mistake limit exceeded")
            }
        }
    }

    private fun cancelPositionGracePeriod() {
        positionGraceJob?.cancel()
        positionGraceJob = null
        FocusSessionManager.update { it.copy(warningSeconds = null) }
    }

    private fun startAppGracePeriod() {
        if (!FocusSessionManager.state.value.active || appGraceJob?.isActive == true) return
        appGraceJob = scope.launch {
            for (second in 5 downTo 1) {
                FocusSessionManager.update { it.copy(appReturnSeconds = second) }
                delay(1_000)
            }
            finishSession(completed = false, failedReason = "App left during session")
        }
    }

    private fun cancelAppGracePeriod() {
        appGraceJob?.cancel()
        appGraceJob = null
        FocusSessionManager.update { it.copy(appReturnSeconds = null) }
    }

    private fun warnUser() {
        scope.launch {
            runCatching {
                val tone = ToneGenerator(AudioManager.STREAM_ALARM, 80)
                tone.startTone(ToneGenerator.TONE_PROP_BEEP2, 350)
                delay(400)
                tone.release()
            }
        }
        runCatching {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getSystemService(VibratorManager::class.java).defaultVibrator
            } else {
                @Suppress("DEPRECATION") getSystemService(VIBRATOR_SERVICE) as Vibrator
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION") vibrator.vibrate(350)
            }
        }
    }

    private fun finishSession(completed: Boolean, failedReason: String?) {
        if (finishing || !FocusSessionManager.state.value.active) return
        finishing = true
        val now = System.currentTimeMillis()
        val runtime = FocusSessionManager.state.value
        val actualMinutes = if (completed) runtime.plannedDurationMinutes else
            ceil((now - startTimeMillis).coerceAtLeast(0) / 60_000.0).toInt().coerceAtMost(runtime.plannedDurationMinutes)
        val result = FocusSession(
            sessionName = runtime.sessionName,
            plannedDurationMinutes = runtime.plannedDurationMinutes,
            actualDurationMinutes = actualMinutes,
            allowedMistakes = runtime.allowedMistakes,
            mistakesUsed = runtime.mistakesUsed,
            completed = completed,
            failedReason = failedReason,
            startTime = startTimeMillis,
            endTime = now
        )
        countdownJob?.cancel()
        positionGraceJob?.cancel()
        appGraceJob?.cancel()
        sensorManager.unregisterListener(this)
        restoreAudioState()
        FocusSessionManager.update { FocusRuntimeState(lastResult = result) }
        scope.launch {
            try {
                withContext(Dispatchers.IO) { repository.save(result) }
            } finally {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
    }

    private fun saveAndApplyAudioState() {
        val prefs = getSharedPreferences(AUDIO_PREFS, MODE_PRIVATE)
        if (prefs.getLong(KEY_SESSION_START, -1L) != startTimeMillis) {
            prefs.edit()
                .putLong(KEY_SESSION_START, startTimeMillis)
                .putInt(KEY_RINGER, audioManager.ringerMode)
                .putInt(KEY_FILTER, notificationManager.currentInterruptionFilter)
                .apply()
        }
        runCatching {
            if (notificationManager.isNotificationPolicyAccessGranted) {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY)
            }
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
        }
    }

    private fun restoreAudioState() {
        val prefs = getSharedPreferences(AUDIO_PREFS, MODE_PRIVATE)
        runCatching { audioManager.ringerMode = prefs.getInt(KEY_RINGER, AudioManager.RINGER_MODE_NORMAL) }
        runCatching {
            if (notificationManager.isNotificationPolicyAccessGranted) {
                notificationManager.setInterruptionFilter(
                    prefs.getInt(KEY_FILTER, NotificationManager.INTERRUPTION_FILTER_ALL)
                )
            }
        }
        prefs.edit().clear().apply()
    }

    private fun startAsForeground() {
        ServiceCompat.startForeground(
            this,
            NOTIFICATION_ID,
            buildNotification(),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE else 0
        )
    }

    private fun updateNotification() {
        runCatching { notificationManager.notify(NOTIFICATION_ID, buildNotification()) }
    }

    private fun buildNotification(): android.app.Notification {
        val state = FocusSessionManager.state.value
        val remainingSeconds = state.remainingMillis / 1_000
        val time = "%02d:%02d".format(remainingSeconds / 60, remainingSeconds % 60)
        val openIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val endIntent = PendingIntent.getService(
            this, 1, Intent(this, FocusSessionService::class.java).setAction(ACTION_END),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_tsuzuku_notification)
            .setContentTitle(state.sessionName.ifBlank { "Tsuzuku Focus" })
            .setContentText("$time remaining • ${state.mistakesUsed} mistakes")
            .setContentIntent(openIntent)
            .addAction(0, "End session", endIntent)
            .setOnlyAlertOnce(true)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    private fun createNotificationChannel() {
        notificationManager.createNotificationChannel(
            NotificationChannel(CHANNEL_ID, "Focus sessions", NotificationManager.IMPORTANCE_LOW).apply {
                description = "Keeps active focus sessions running"
                setSound(null, null)
            }
        )
    }

    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        scope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val CHANNEL_ID = "focus_sessions"
        private const val NOTIFICATION_ID = 2026
        private const val AUDIO_PREFS = "focus_audio_state"
        private const val KEY_RINGER = "ringer"
        private const val KEY_FILTER = "filter"
        private const val KEY_SESSION_START = "session_start"
        const val ACTION_START = "com.agupta07505.tsuzuku.focus.START"
        const val ACTION_END = "com.agupta07505.tsuzuku.focus.END"
        const val ACTION_APP_BACKGROUND = "com.agupta07505.tsuzuku.focus.BACKGROUND"
        const val ACTION_APP_FOREGROUND = "com.agupta07505.tsuzuku.focus.FOREGROUND"
        const val EXTRA_NAME = "name"
        const val EXTRA_DURATION = "duration"
        const val EXTRA_ALLOWED = "allowed"
        const val EXTRA_START_TIME = "start_time"

        fun send(context: Context, action: String) {
            context.startService(Intent(context, FocusSessionService::class.java).setAction(action))
        }
    }
}
