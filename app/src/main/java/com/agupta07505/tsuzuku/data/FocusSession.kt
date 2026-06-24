package com.agupta07505.tsuzuku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "focus_sessions")
data class FocusSession(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionName: String,
    val plannedDurationMinutes: Int,
    val actualDurationMinutes: Int,
    val allowedMistakes: Int,
    val mistakesUsed: Int,
    val completed: Boolean,
    val failedReason: String?,
    val startTime: Long,
    val endTime: Long
)
