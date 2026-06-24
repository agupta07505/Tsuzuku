package com.agupta07505.tsuzuku.ui

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agupta07505.tsuzuku.data.AppDatabase
import com.agupta07505.tsuzuku.data.FocusRepository
import com.agupta07505.tsuzuku.data.FocusSession
import com.agupta07505.tsuzuku.focus.FocusRuntimeState
import com.agupta07505.tsuzuku.focus.FocusSessionManager
import com.agupta07505.tsuzuku.focus.FocusSessionService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar

data class FocusUiState(
    val todayFocusMinutes: Int = 0,
    val totalFocusMinutes: Int = 0,
    val completedSessions: Int = 0,
    val failedSessions: Int = 0,
    val successRate: Int = 0,
    val recentSessions: List<FocusSession> = emptyList(),
    val runtime: FocusRuntimeState = FocusRuntimeState()
)

class FocusViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FocusRepository(AppDatabase.getDatabase(application).focusSessionDao())

    val uiState: StateFlow<FocusUiState> = combine(repository.sessions, FocusSessionManager.state) { sessions, runtime ->
        val now = System.currentTimeMillis()
        val completed = sessions.count { it.completed }
        val failed = sessions.size - completed
        FocusUiState(
            todayFocusMinutes = sessions.filter {
                it.completed && isSameLocalDay(it.endTime, now)
            }.sumOf { it.actualDurationMinutes },
            totalFocusMinutes = sessions.filter { it.completed }.sumOf { it.actualDurationMinutes },
            completedSessions = completed,
            failedSessions = failed,
            successRate = if (sessions.isEmpty()) 0 else completed * 100 / sessions.size,
            recentSessions = sessions.take(10),
            runtime = runtime
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), FocusUiState())

    fun startSession(name: String, durationMinutes: Int, allowedMistakes: Int) {
        val context = getApplication<Application>()
        val intent = Intent(context, FocusSessionService::class.java).apply {
            action = FocusSessionService.ACTION_START
            putExtra(FocusSessionService.EXTRA_NAME, name.trim().ifBlank { "Focus Session" })
            putExtra(FocusSessionService.EXTRA_DURATION, durationMinutes.coerceAtLeast(1))
            putExtra(FocusSessionService.EXTRA_ALLOWED, allowedMistakes.coerceAtLeast(0))
            putExtra(FocusSessionService.EXTRA_START_TIME, System.currentTimeMillis())
        }
        ContextCompat.startForegroundService(context, intent)
    }

    fun endSession() {
        FocusSessionService.send(getApplication(), FocusSessionService.ACTION_END)
    }

    fun dismissResult() = FocusSessionManager.clearResult()

    private fun isSameLocalDay(firstMillis: Long, secondMillis: Long): Boolean {
        val first = Calendar.getInstance().apply { timeInMillis = firstMillis }
        val second = Calendar.getInstance().apply { timeInMillis = secondMillis }
        return first.get(Calendar.ERA) == second.get(Calendar.ERA) &&
            first.get(Calendar.YEAR) == second.get(Calendar.YEAR) &&
            first.get(Calendar.DAY_OF_YEAR) == second.get(Calendar.DAY_OF_YEAR)
    }
}
