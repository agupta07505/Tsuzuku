/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import kotlinx.coroutines.flow.Flow

class CountdownDateRepository(private val dao: CountdownDateDao) {
    val allCountdowns: Flow<List<CountdownDateEntity>> = dao.getAllCountdowns()

    fun getCountdownById(id: Long): Flow<CountdownDateEntity?> = dao.getCountdownById(id)

    suspend fun getAllCountdownsDirect(): List<CountdownDateEntity> = dao.getAllCountdownsDirect()

    suspend fun getCountdownByIdDirect(id: Long): CountdownDateEntity? = dao.getCountdownByIdDirect(id)

    suspend fun insertCountdown(countdown: CountdownDateEntity): Long = dao.insertCountdown(countdown)

    suspend fun updateCountdown(countdown: CountdownDateEntity) {
        dao.updateCountdown(countdown.copy(updatedAtMillis = System.currentTimeMillis()))
    }

    suspend fun deleteCountdown(countdown: CountdownDateEntity) = dao.deleteCountdown(countdown)
}
