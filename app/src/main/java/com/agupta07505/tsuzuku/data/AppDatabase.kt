/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Habit::class, HabitLog::class, FocusSession::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun focusSessionDao(): FocusSessionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "streak_marker_db"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """CREATE TABLE IF NOT EXISTS `focus_sessions` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `sessionName` TEXT NOT NULL,
                        `plannedDurationMinutes` INTEGER NOT NULL,
                        `actualDurationMinutes` INTEGER NOT NULL,
                        `allowedMistakes` INTEGER NOT NULL,
                        `mistakesUsed` INTEGER NOT NULL,
                        `completed` INTEGER NOT NULL,
                        `failedReason` TEXT,
                        `startTime` INTEGER NOT NULL,
                        `endTime` INTEGER NOT NULL
                    )""".trimIndent()
                )
            }
        }
    }
}
