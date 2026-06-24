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
    private val focusRepository: FocusRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = HabitRepository(database.habitDao())
        focusRepository = FocusRepository(database.focusSessionDao())
    }

    val habits: StateFlow<List<Habit>> = repository.allActiveHabits
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val managedHabits: StateFlow<List<Habit>> = repository.allHabits
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

    fun setHabitArchived(habit: Habit, archived: Boolean) {
        viewModelScope.launch {
            val updated = habit.copy(isArchived = archived)
            repository.updateHabit(updated)
            val context = getApplication<Application>().applicationContext
            HabitNotificationHelper.cancelReminder(context, habit.id)
            if (!archived && updated.reminderHour != null && updated.reminderMinute != null) {
                HabitNotificationHelper.scheduleDailyReminder(context, updated)
            }
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
        val allLogsList = repository.getAllLogsDirect()
        val allFocusSessions = focusRepository.getAllDirect()
        
        val root = JSONObject().put("backupVersion", 2)
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
        root.put("focusSessions", JSONArray().apply {
            allFocusSessions.forEach { session ->
                put(JSONObject().apply {
                    put("id", session.id)
                    put("sessionName", session.sessionName)
                    put("plannedDurationMinutes", session.plannedDurationMinutes)
                    put("actualDurationMinutes", session.actualDurationMinutes)
                    put("allowedMistakes", session.allowedMistakes)
                    put("mistakesUsed", session.mistakesUsed)
                    put("completed", session.completed)
                    put("failedReason", session.failedReason ?: JSONObject.NULL)
                    put("startTime", session.startTime)
                    put("endTime", session.endTime)
                })
            }
        })
        
        return root.toString(2)
    }

    suspend fun exportDataCsv(): String {
        val allHabits = repository.getAllHabitsDirectDecrypted()
        val allLogsList = repository.getAllLogsDirect()
        val allFocusSessions = focusRepository.getAllDirect()
        
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

        sb.append("\n--- FOCUS SESSIONS TABLE ---\n")
        sb.append("ID,SessionName,PlannedMinutes,ActualMinutes,AllowedMistakes,MistakesUsed,Completed,FailedReason,StartTime,EndTime\n")
        for (session in allFocusSessions) {
            sb.append("${session.id},\"${escapeCsvField(session.sessionName)}\",${session.plannedDurationMinutes},${session.actualDurationMinutes},${session.allowedMistakes},${session.mistakesUsed},${session.completed},\"${escapeCsvField(session.failedReason.orEmpty())}\",${session.startTime},${session.endTime}\n")
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
                focusRepository.clearAll()
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
                val focusSessionsArray = root.optJSONArray("focusSessions")
                
                val context = getApplication<Application>().applicationContext
                val db = AppDatabase.getDatabase(context)
                
                // Clear existing using explicit DAO truncation
                repository.clearAllData()
                focusRepository.clearAll()
                
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

                if (focusSessionsArray != null) {
                    for (i in 0 until focusSessionsArray.length()) {
                        val item = focusSessionsArray.getJSONObject(i)
                        focusRepository.save(
                            FocusSession(
                                id = item.optLong("id", 0L),
                                sessionName = item.getString("sessionName"),
                                plannedDurationMinutes = item.getInt("plannedDurationMinutes"),
                                actualDurationMinutes = item.getInt("actualDurationMinutes"),
                                allowedMistakes = item.getInt("allowedMistakes"),
                                mistakesUsed = item.getInt("mistakesUsed"),
                                completed = item.getBoolean("completed"),
                                failedReason = if (item.isNull("failedReason")) null else item.getString("failedReason"),
                                startTime = item.getLong("startTime"),
                                endTime = item.getLong("endTime")
                            )
                        )
                    }
                }
                
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e.localizedMessage ?: "Invalid JSON backup structure")
            }
        }
    }
}
