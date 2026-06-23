/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String, // Encrypted in database, decrypted in UI
    val description: String = "", // Encrypted in database, decrypted in UI
    val colorHex: String = "#4CAF50", // Hex color representing the habit
    val iconName: String = "Star", // Icon category name
    val reminderHour: Int? = null,
    val reminderMinute: Int? = null,
    val isArchived: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) : Serializable
