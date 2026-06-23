/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agupta07505.tsuzuku.data.*
import com.agupta07505.tsuzuku.notification.HabitNotificationHelper
import com.agupta07505.tsuzuku.util.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabitRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = HabitRepository(database.habitDao())
    }

    val habits: StateFlow<List<Habit>> = repository.allActiveHabits
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allLogs: StateFlow<List<HabitLog>> = repository.allLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // State of selected date for checklist checking (Default is today)
    private val _selectedDate = MutableStateFlow(DateUtils.getTodayString())
    val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun addHabit(
        name: String,
        description: String,
        colorHex: String,
        iconName: String,
        reminderHour: Int?,
        reminderMinute: Int?
    ) {
        viewModelScope.launch {
            val habit = Habit(
                name = name,
                description = description,
                colorHex = colorHex,
                iconName = iconName,
                reminderHour = reminderHour,
                reminderMinute = reminderMinute
            )
            val id = repository.insertHabit(habit)
            
            // Schedule local reminder if specified
            if (reminderHour != null && reminderMinute != null) {
                val context = getApplication<Application>().applicationContext
                val savedHabit = habit.copy(id = id.toInt())
                HabitNotificationHelper.scheduleDailyReminder(context, savedHabit)
            }
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            repository.updateHabit(habit)
            
            // Re-schedule reminder alarm
            val context = getApplication<Application>().applicationContext
            HabitNotificationHelper.cancelReminder(context, habit.id)
            if (habit.reminderHour != null && habit.reminderMinute != null) {
                HabitNotificationHelper.scheduleDailyReminder(context, habit)
            }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
            val context = getApplication<Application>().applicationContext
            HabitNotificationHelper.cancelReminder(context, habit.id)
        }
    }

    fun toggleHabitLog(habitId: Int, date: String, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.toggleLog(habitId, date, isCompleted)
        }
    }

    fun triggerTestNotification(habitName: String) {
        val context = getApplication<Application>().applicationContext
        HabitNotificationHelper.triggerImmediateMockNotification(context, habitName)
    }

    // Export Portability Methods
    suspend fun exportDataJson(): String {
        val allHabits = repository.getAllHabitsDirectDecrypted()
        val allLogsList = allLogs.value
        
        val root = JSONObject()
        val habitsArray = JSONArray()
        for (h in allHabits) {
            val hObj = JSONObject().apply {
                put("id", h.id)
                put("name", h.name)
                put("description", h.description)
                put("colorHex", h.colorHex)
                put("iconName", h.iconName)
                put("reminderHour", h.reminderHour ?: JSONObject.NULL)
                put("reminderMinute", h.reminderMinute ?: JSONObject.NULL)
                put("isArchived", h.isArchived)
                put("createdAt", h.createdAt)
            }
            habitsArray.put(hObj)
        }
        
        val logsArray = JSONArray()
        for (l in allLogsList) {
            val lObj = JSONObject().apply {
                put("habitId", l.habitId)
                put("date", l.date)
                put("isCompleted", l.isCompleted)
            }
            logsArray.put(lObj)
        }
        
        root.put("habits", habitsArray)
        root.put("logs", logsArray)
        
        return root.toString(2)
    }

    suspend fun exportDataCsv(): String {
        val allHabits = repository.getAllHabitsDirectDecrypted()
        val allLogsList = allLogs.value
        
        val sb = StringBuilder()
        sb.append("--- HABITS TABLE ---\n")
        sb.append("ID,Name,Description,ColorHex,IconName,ReminderHour,ReminderMinute,IsArchived,CreatedAt\n")
        for (h in allHabits) {
            val cleanName = escapeCsvField(h.name)
            val cleanDesc = escapeCsvField(h.description)
            sb.append("${h.id},\"$cleanName\",\"$cleanDesc\",${h.colorHex},${h.iconName},${h.reminderHour ?: ""},${h.reminderMinute ?: ""},${h.isArchived},${h.createdAt}\n")
        }
        
        sb.append("\n--- HABIT LOGS TABLE ---\n")
        sb.append("HabitID,Date,IsCompleted\n")
        for (l in allLogsList) {
            sb.append("${l.habitId},${l.date},${l.isCompleted}\n")
        }
        
        return sb.toString()
    }

    private fun escapeCsvField(value: String): String {
        return value.replace("\"", "\"\"")
    }

    fun deleteAllLocalData(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.clearAllData()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun importDataJson(jsonString: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val root = JSONObject(jsonString)
                val habitsArray = root.getJSONArray("habits")
                val logsArray = root.getJSONArray("logs")
                
                val context = getApplication<Application>().applicationContext
                val db = AppDatabase.getDatabase(context)
                
                // Clear existing using explicit DAO truncation
                repository.clearAllData()
                
                for (i in 0 until habitsArray.length()) {
                    val ho = habitsArray.getJSONObject(i)
                    val rHour = if (ho.isNull("reminderHour")) null else ho.getInt("reminderHour")
                    val rMin = if (ho.isNull("reminderMinute")) null else ho.getInt("reminderMinute")
                    val habit = Habit(
                        id = ho.getInt("id"),
                        name = ho.getString("name"),
                        description = ho.optString("description", ""),
                        colorHex = ho.optString("colorHex", "#4CAF50"),
                        iconName = ho.optString("iconName", "Star"),
                        reminderHour = rHour,
                        reminderMinute = rMin,
                        isArchived = ho.optBoolean("isArchived", false),
                        createdAt = ho.optLong("createdAt", System.currentTimeMillis())
                    )
                    repository.insertHabit(habit)
                    
                    if (rHour != null && rMin != null) {
                        HabitNotificationHelper.scheduleDailyReminder(context, habit)
                    }
                }
                
                for (i in 0 until logsArray.length()) {
                    val lo = logsArray.getJSONObject(i)
                    val log = HabitLog(
                        habitId = lo.getInt("habitId"),
                        date = lo.getString("date"),
                        isCompleted = lo.getBoolean("isCompleted")
                    )
                    db.habitDao().insertLog(log)
                }
                
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e.localizedMessage ?: "Invalid JSON backup structure")
            }
        }
    }
}
