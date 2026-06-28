/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countdown_dates")
data class CountdownDateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val targetDateMillis: Long,
    val notes: String? = null,
    val iconKey: String = "calendar",
    val accentColor: Long = 0xFF8FEA3C,
    val createdAtMillis: Long = System.currentTimeMillis(),
    val updatedAtMillis: Long = System.currentTimeMillis()
)
