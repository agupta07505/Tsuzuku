/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import com.agupta07505.tsuzuku.security.SecurityHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepository(private val habitDao: HabitDao) {

    val allActiveHabits: Flow<List<Habit>> = habitDao.getAllActiveHabits().map { habits ->
        habits.map { decryptHabit(it) }
    }

    val allLogs: Flow<List<HabitLog>> = habitDao.getAllLogs()

    suspend fun getAllLogsDirect(): List<HabitLog> = habitDao.getAllLogsDirect()

    suspend fun getAllHabitsDirectDecrypted(): List<Habit> {
        return habitDao.getAllHabitsDirect().map { decryptHabit(it) }
    }

    suspend fun insertHabit(habit: Habit): Long {
        val encrypted = encryptHabit(habit)
        return habitDao.insertHabit(encrypted)
    }

    suspend fun updateHabit(habit: Habit) {
        val encrypted = encryptHabit(habit)
        habitDao.updateHabit(encrypted)
    }

    suspend fun deleteHabit(habit: Habit) {
        // Also clean up any logs for this habit just in case Cascade deletes are delayed
        habitDao.deleteLogsForHabit(habit.id)
        habitDao.deleteHabit(habit)
    }

    suspend fun getHabitById(id: Int): Habit? {
        return habitDao.getHabitById(id)?.let { decryptHabit(it) }
    }

    suspend fun clearAllData() {
        habitDao.deleteAllLogs()
        habitDao.deleteAllHabits()
    }

    // Logs Operations
    fun getLogsForHabit(habitId: Int): Flow<List<HabitLog>> = habitDao.getLogsForHabit(habitId)

    suspend fun getLogsForHabitDirect(habitId: Int): List<HabitLog> = habitDao.getLogsForHabitDirect(habitId)

    suspend fun toggleLog(habitId: Int, date: String, isCompleted: Boolean) {
        if (isCompleted) {
            habitDao.insertLog(HabitLog(habitId = habitId, date = date, isCompleted = true))
        } else {
            habitDao.deleteLog(habitId, date)
        }
    }

    // Encryption Helpers
    private fun encryptHabit(habit: Habit): Habit {
        return habit.copy(
            name = SecurityHelper.encrypt(habit.name),
            description = SecurityHelper.encrypt(habit.description)
        )
    }

    private fun decryptHabit(habit: Habit): Habit {
        return habit.copy(
            name = SecurityHelper.decrypt(habit.name),
            description = SecurityHelper.decrypt(habit.description)
        )
    }
}
