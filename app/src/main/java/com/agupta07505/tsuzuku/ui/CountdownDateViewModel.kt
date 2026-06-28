/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agupta07505.tsuzuku.data.AppDatabase
import com.agupta07505.tsuzuku.data.CountdownDateEntity
import com.agupta07505.tsuzuku.data.CountdownDateRepository
import com.agupta07505.tsuzuku.widget.CountdownDateWidgetProvider
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CountdownDateUiState(
    val countdowns: List<CountdownDateEntity> = emptyList()
)

class CountdownDateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CountdownDateRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = CountdownDateRepository(database.countdownDateDao())
    }

    val countdowns: StateFlow<List<CountdownDateEntity>> = repository.allCountdowns
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addCountdown(title: String, targetDateMillis: Long, notes: String?, iconKey: String, accentColor: Long, onSaved: () -> Unit) {
        viewModelScope.launch {
            repository.insertCountdown(
                CountdownDateEntity(
                    title = title.trim().take(30),
                    targetDateMillis = targetDateMillis,
                    notes = notes?.trim()?.take(100)?.ifBlank { null },
                    iconKey = iconKey,
                    accentColor = accentColor
                )
            )
            CountdownDateWidgetProvider.updateAll(getApplication())
            onSaved()
        }
    }

    fun updateCountdown(countdown: CountdownDateEntity, title: String, targetDateMillis: Long, notes: String?, iconKey: String, accentColor: Long, onSaved: () -> Unit) {
        viewModelScope.launch {
            repository.updateCountdown(
                countdown.copy(
                    title = title.trim().take(30),
                    targetDateMillis = targetDateMillis,
                    notes = notes?.trim()?.take(100)?.ifBlank { null },
                    iconKey = iconKey,
                    accentColor = accentColor
                )
            )
            CountdownDateWidgetProvider.updateAll(getApplication())
            onSaved()
        }
    }

    fun deleteCountdown(countdown: CountdownDateEntity) {
        viewModelScope.launch {
            repository.deleteCountdown(countdown)
            CountdownDateWidgetProvider.updateAll(getApplication())
        }
    }
}
