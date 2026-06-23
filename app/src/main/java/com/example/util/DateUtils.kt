package com.example.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getTodayString(): String {
        return dateFormat.format(Date())
    }

    fun getFormattedDate(calendar: Calendar): String {
        return dateFormat.format(calendar.time)
    }

    fun parseDateString(dateStr: String): Date? {
        return try {
            dateFormat.parse(dateStr)
        } catch (e: Exception) {
            null
        }
    }

    // Returns a list of dates for the last 30 days relative to today
    fun getLast30Days(): List<String> {
        val dates = mutableListOf<String>()
        val cal = Calendar.getInstance()
        for (i in 0 until 30) {
            dates.add(dateFormat.format(cal.time))
            cal.add(Calendar.DAY_OF_YEAR, -1)
        }
        return dates.reversed()
    }

    fun getLastNDays(n: Int): List<String> {
        val dates = mutableListOf<String>()
        val cal = Calendar.getInstance()
        val limit = n.coerceIn(1, 30)
        for (i in 0 until limit) {
            dates.add(dateFormat.format(cal.time))
            cal.add(Calendar.DAY_OF_YEAR, -1)
        }
        return dates
    }

    // Returns a date string with offset in days
    fun getDateWithOffset(offset: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, offset)
        return dateFormat.format(cal.time)
    }

    // Returns months back from current month, structured as Year, Month index, e.g. (2026, Calendar.JUNE)
    fun getRecentMonths(count: Int): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        val cal = Calendar.getInstance()
        for (i in 0 until count) {
            list.add(Pair(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)))
            cal.add(Calendar.MONTH, -1)
        }
        return list
    }

    // Returns List of dates (YYYY-MM-DD) for a specific month/year grid (including padded cells to match day of week)
    fun getMonthGridDates(year: Int, month: Int): List<String?> {
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }
        
        // Day of week of the 1st of the month (1 = Sunday, 2 = Monday, ...)
        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        
        // Number of preceding empty cells
        // Let's assume Monday is start of week (0 to 6)
        // firstDayOfWeek: Sunday (1) -> 6, Monday (2) -> 0, Tuesday (3) -> 1, ...
        val emptyLeadingCells = if (firstDayOfWeek == Calendar.SUNDAY) 6 else firstDayOfWeek - 2
        
        val maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val cells = mutableListOf<String?>()
        
        for (i in 0 until emptyLeadingCells) {
            cells.add(null)
        }
        
        for (day in 1..maxDays) {
            cal.set(Calendar.DAY_OF_MONTH, day)
            cells.add(dateFormat.format(cal.time))
        }
        
        return cells
    }

    fun getMonthName(monthIndex: Int): String {
        val cal = Calendar.getInstance().apply {
            set(Calendar.MONTH, monthIndex)
        }
        return SimpleDateFormat("MMMM", Locale.US).format(cal.time)
    }
}
