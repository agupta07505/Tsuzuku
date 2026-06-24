package com.agupta07505.tsuzuku.data

import kotlinx.coroutines.flow.Flow

class FocusRepository(private val dao: FocusSessionDao) {
    val sessions: Flow<List<FocusSession>> = dao.observeAll()

    suspend fun save(session: FocusSession): Long = dao.insert(session)

    suspend fun getAllDirect(): List<FocusSession> = dao.getAllDirect()

    suspend fun clearAll() = dao.deleteAll()
}
