/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CountdownDateDao {
    @Query("SELECT * FROM countdown_dates ORDER BY targetDateMillis ASC, id DESC")
    fun getAllCountdowns(): Flow<List<CountdownDateEntity>>

    @Query("SELECT * FROM countdown_dates ORDER BY targetDateMillis ASC, id DESC")
    suspend fun getAllCountdownsDirect(): List<CountdownDateEntity>

    @Query("SELECT * FROM countdown_dates WHERE id = :id")
    fun getCountdownById(id: Long): Flow<CountdownDateEntity?>

    @Query("SELECT * FROM countdown_dates WHERE id = :id")
    suspend fun getCountdownByIdDirect(id: Long): CountdownDateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountdown(countdown: CountdownDateEntity): Long

    @Update
    suspend fun updateCountdown(countdown: CountdownDateEntity)

    @Delete
    suspend fun deleteCountdown(countdown: CountdownDateEntity)
}
