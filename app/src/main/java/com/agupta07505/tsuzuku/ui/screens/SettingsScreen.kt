/*
 * Tsuzuku (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import androidx.compose.ui.res.painterResource
import com.agupta07505.tsuzuku.R
import com.agupta07505.tsuzuku.data.Quotes
import com.agupta07505.tsuzuku.ui.HabitViewModel
import com.agupta07505.tsuzuku.util.DateUtils
import com.agupta07505.tsuzuku.util.StreakCalculator
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import java.io.BufferedReader
import java.io.InputStreamReader
import com.agupta07505.tsuzuku.BuildConfig

private data class ThemeOption(val key: String, val label: String, val colors: List<Color>)

@Composable
private fun ThemePreviewDots(colors: List<Color>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
            )
        }
    }
}

@Composable
private fun SocialBadge(
    label: String,
    @DrawableRes iconRes: Int,
    url: String,
    isEmail: Boolean = false,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                try {
                    val intent = if (isEmail) {
                        Intent(Intent.ACTION_SENDTO).apply {
                            data = android.net.Uri.parse(url)
                        }
                    } else {
                        Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url))
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Cannot open $label", Toast.LENGTH_SHORT).show()
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "agupta07505",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CollapsibleSettingsCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    primaryColor: Color = Color(0xFF2CB5C3),
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        ),
        border = BorderStroke(
            1.dp,
            if (isExpanded) primaryColor.copy(alpha = 0.6f) else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggle() }
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Styled circular boundary for section icon
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(primaryColor.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                ) {
                    Divider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    content()
                }
            }
        }
    }
}

@Composable
private fun CollapsibleSettingsCardDanger(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.10f)
        ),
        border = BorderStroke(
            1.dp,
            if (isExpanded) MaterialTheme.colorScheme.error.copy(alpha = 0.6f) else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggle() }
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF43F5E).copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color(0xFFF43F5E),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF43F5E)
                    )
                }
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = Color(0xFFF43F5E),
                    modifier = Modifier.size(24.dp)
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                ) {
                    Divider(
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.2f),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: HabitViewModel,
    themeState: String,
    onThemeChanged: (String) -> Unit,
    customAccentColorHex: String = "",
    onAccentColorChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sharedPrefs = remember { context.getSharedPreferences("streak_marker_prefs", Context.MODE_PRIVATE) }

    // Consolidated expanded accordion state - only one section can be open at a time
    var expandedSection by remember { mutableStateOf<String?>(null) }
    var showJapaneseQuotes by remember {
        mutableStateOf(sharedPrefs.getBoolean("show_japanese_quotes", true))
    }


    // Dialog state
    var showImportDialog by remember { mutableStateOf(false) }
    var importText by remember { mutableStateOf("") }
    
    var showResetDialog by remember { mutableStateOf(false) }
    var confirmText by remember { mutableStateOf("") }

    var showTimePickerDialog by remember { mutableStateOf(false) }

    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }

    // Local notification parameters loaded/saved dynamically
    var reminderHour by remember { mutableStateOf(sharedPrefs.getInt("reminder_hour", 20)) }
    var reminderMinute by remember { mutableStateOf(sharedPrefs.getInt("reminder_minute", 0)) }
    
    // Convert 24-hour hour/minute values to pretty 12-hour AM/PM string representation
    val formattedReminderTime = remember(reminderHour, reminderMinute) {
        val amPmStr = if (reminderHour >= 12) "PM" else "AM"
        val displayHour = when {
            reminderHour == 0 -> 12
            reminderHour > 12 -> reminderHour - 12
            else -> reminderHour
        }
        String.format("%02d:%02d %s", displayHour, reminderMinute, amPmStr)
    }

    // Dynamic Database Stats Streams
    val habitsList by viewModel.habits.collectAsState()
    val logsList by viewModel.allLogs.collectAsState()

    // Calculate real App Statistics
    val totalHabitsCount = habitsList.size
    val totalCheckInsCount = logsList.filter { it.isCompleted }.size
    
    val daysUsingCount = remember(logsList) {
        val uniqueActiveDates = logsList.map { it.date }.distinct().size
        if (uniqueActiveDates > 0) uniqueActiveDates else 1
    }

    val bestStreakCount = remember(logsList) {
        val completedDatesList = logsList.filter { it.isCompleted }
            .map { it.date }
            .distinct()
            .mapNotNull { DateUtils.parseDateString(it) }
            .sorted()
        
        if (completedDatesList.isEmpty()) {
            0
        } else {
            val msInDay = 24 * 60 * 60 * 1000L
            var maxStr = 0
            var tempStr = 0
            var lastDt: Date? = null

            for (date in completedDatesList) {
                if (lastDt == null) {
                    tempStr = 1
                } else {
                    val diffMs = date.time - lastDt.time
                    val diffDays = (diffMs.toDouble() / msInDay).toInt()
                    
                    if (diffDays == 1) {
                        tempStr++
                    } else if (diffDays > 1) {
                        if (tempStr > maxStr) {
                            maxStr = tempStr
                        }
                        tempStr = 1
                    }
                }
                lastDt = date
            }
            if (tempStr > maxStr) {
                maxStr = tempStr
            }
            maxStr
        }
    }

    // Backup details persisted & loaded
    var lastBackupDate by remember {
        mutableStateOf(sharedPrefs.getString("last_backup_date", "June 23, 2026") ?: "June 23, 2026")
    }
    var lastBackupSize by remember {
        mutableStateOf(sharedPrefs.getString("last_backup_size", "124 KB") ?: "124 KB")
    }

    // Sharing Exports Action
    fun shareBackup(content: String, fileName: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, fileName)
        }
        val shareIntent = Intent.createChooser(sendIntent, "Export Streak Backup")
        context.startActivity(shareIntent)

        // Dynamically save last backup markers matching reality
        val currentFormattedDate = SimpleDateFormat("MMMM d, yyyy", Locale.US).format(Date())
        val computedSize = "${content.toByteArray().size / 1024 + 1} KB"
        
        sharedPrefs.edit()
            .putString("last_backup_date", currentFormattedDate)
            .putString("last_backup_size", computedSize)
            .apply()

        lastBackupDate = currentFormattedDate
        lastBackupSize = computedSize
    }

    val exportJsonLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri: Uri? ->
        if (uri != null) {
            coroutineScope.launch {
                try {
                    val jsonContent = viewModel.exportDataJson()
                    context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                        outputStream.write(jsonContent.toByteArray())
                    }
                    Toast.makeText(context, "Backup JSON file saved successfully!", Toast.LENGTH_LONG).show()
                    
                    val currentFormattedDate = SimpleDateFormat("MMMM d, yyyy", Locale.US).format(Date())
                    val computedSize = "${jsonContent.toByteArray().size / 1024 + 1} KB"
                    sharedPrefs.edit()
                        .putString("last_backup_date", currentFormattedDate)
                        .putString("last_backup_size", computedSize)
                        .apply()
                        
                    lastBackupDate = currentFormattedDate
                    lastBackupSize = computedSize
                } catch (e: Exception) {
                    Toast.makeText(context, "Failed to save backup: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    val importJsonFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            coroutineScope.launch {
                try {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder = java.lang.StringBuilder()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line).append("\n")
                        line = reader.readLine()
                    }
                    inputStream?.close()
                    val jsonContent = stringBuilder.toString().trim()
                    
                    if (jsonContent.isNotEmpty()) {
                        viewModel.importDataJson(
                            jsonContent,
                            onSuccess = {
                                showImportDialog = false
                                Toast.makeText(context, "Backup successfully restored!", Toast.LENGTH_LONG).show()
                            },
                            onError = { err ->
                                Toast.makeText(context, "Restore Error: $err", Toast.LENGTH_LONG).show()
                            }
                        )
                    } else {
                        Toast.makeText(context, "Selected file is empty!", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Failed to read backup file: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("settings_scroll"),
            contentPadding = PaddingValues(top = 22.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Screen Header Info
            item {
                Column {
                    Text(
                        text = "Settings",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Configure visual elements and backups locally",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            
            // 🎨 Themes Option Accordion
            item {
                CollapsibleSettingsCard(
                    title = "Appearance",
                    icon = Icons.Default.Edit,
                    isExpanded = expandedSection == "appearance",
                    onToggle = { expandedSection = if (expandedSection == "appearance") null else "appearance" },
                    primaryColor = Color(0xFF2CB5C3)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Choose your preferred visual theme aesthetic",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        
                        val themeOptions = listOf(
                            ThemeOption("system", "🌅 System", listOf(Color(0xFFF8FAFC), Color(0xFF0F171F), Color(0xFF94A3B8))),
                            ThemeOption("light", "🌞 Light", listOf(Color(0xFFE2E8F0), Color(0xFFF8FAFC), Color(0xFF2CB5C3))),
                            ThemeOption("dark", "🌃 Dark", listOf(Color(0xFF090E14), Color(0xFF16222F), Color(0xFF2CB5C3))),
                            ThemeOption("amoled", "⚫ AMOLED Black", listOf(Color(0xFF000000), Color(0xFF090E14), Color(0xFF2CB5C3))),
                            ThemeOption("green", "🌱 Tsuzuku Green Theme", listOf(Color(0xFF050E09), Color(0xFF122C1E), Color(0xFF22C55E))),
                            ThemeOption("blue", "🌊 Tsuzuku Blue Theme", listOf(Color(0xFF030914), Color(0xFF0E223F), Color(0xFF38BDF8)))
                        )
                        
                        themeOptions.forEach { option ->
                            val isSelected = themeState == option.key
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isSelected) Color(0xFF14222F) else Color.Transparent
                                    )
                                    .clickable { onThemeChanged(option.key) }
                                    .padding(vertical = 10.dp, horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { onThemeChanged(option.key) },
                                        modifier = Modifier.testTag("radio_theme_${option.key}"),
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF2CB5C3)
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = option.label,
                                        fontSize = 14.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                ThemePreviewDots(colors = option.colors)
                            }
                        }

                    }
                }
            }
            
            // 🔔 Notifications Option Accordion
            item {
                CollapsibleSettingsCard(
                    title = "Notifications",
                    icon = Icons.Default.Notifications,
                    isExpanded = expandedSection == "notifications",
                    onToggle = { expandedSection = if (expandedSection == "notifications") null else "notifications" },
                    primaryColor = Color(0xFFEAB308)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        var remindersEnabled by remember { mutableStateOf(sharedPrefs.getBoolean("reminders_active", true)) }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Daily Habit Alerts",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Receive notifications to keep up with active habits",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            Switch(
                                checked = remindersEnabled,
                                onCheckedChange = { isActive ->
                                    remindersEnabled = isActive
                                    sharedPrefs.edit().putBoolean("reminders_active", isActive).apply()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFEAB308)
                                )
                            )
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Permanent Motivation",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Show an ongoing status bar notification that updates hourly with different Japanese / English mantras",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            var permanentNotificationEnabled by remember {
                                mutableStateOf(sharedPrefs.getBoolean("permanent_notification_active", false))
                            }
                            Switch(
                                checked = permanentNotificationEnabled,
                                onCheckedChange = { isActive ->
                                    permanentNotificationEnabled = isActive
                                    sharedPrefs.edit().putBoolean("permanent_notification_active", isActive).apply()
                                    if (isActive) {
                                        com.agupta07505.tsuzuku.notification.HabitNotificationHelper.updatePermanentNotification(context)
                                        com.agupta07505.tsuzuku.notification.HabitNotificationHelper.scheduleNextHourlyUpdate(context)
                                    } else {
                                        com.agupta07505.tsuzuku.notification.HabitNotificationHelper.cancelPermanentNotification(context)
                                    }
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFEAB308)
                                )
                            )
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        // Added clickable time picker row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                                .clickable { showTimePickerDialog = true }
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Reminder Time",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Hour and minute daily alerts execute",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFEAB308).copy(alpha = 0.12f))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = formattedReminderTime,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEAB308)
                                )
                            }
                        }
                        
                        Divider(color = Color(0xFF16222F))
                        
                        Button(
                            onClick = {
                                viewModel.triggerTestNotification("🏋️ Daily Reading habit checklist")
                                Toast.makeText(context, "Local alert simulation triggered!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_trigger_mock_notification"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFEAB308),
                                contentColor = Color(0xFF0F171F)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("🔔 Send Test Notification", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            
            // 🌱 Personalization Option Accordion
            item {
                CollapsibleSettingsCard(
                    title = "Personalization",
                    icon = Icons.Default.Settings,
                    isExpanded = expandedSection == "personalization",
                    onToggle = { expandedSection = if (expandedSection == "personalization") null else "personalization" },
                    primaryColor = Color(0xFF22C55E)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        var confettiEnabled by remember {
                            mutableStateOf(sharedPrefs.getBoolean("confetti_active", true))
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Confetti Celebrations",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Burst colorful particles when completing today's habits",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            Switch(
                                checked = confettiEnabled,
                                onCheckedChange = { isAct ->
                                    confettiEnabled = isAct
                                    sharedPrefs.edit().putBoolean("confetti_active", isAct).apply()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF22C55E)
                                )
                            )
                        }
                        
                        Divider(color = MaterialTheme.colorScheme.outlineVariant)
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Japanese Motto",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Include Japanese calligraphy motto at bottom of tracker list",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            Switch(
                                checked = showJapaneseQuotes,
                                onCheckedChange = { isAct ->
                                    showJapaneseQuotes = isAct
                                    sharedPrefs.edit().putBoolean("show_japanese_quotes", isAct).apply()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF22C55E)
                                )
                            )
                        }
                    }
                }
            }
            
            // 💬 Motivation Option Accordion (Interative Quote cards + translations)
            item {
                CollapsibleSettingsCard(
                    title = "Motivation",
                    icon = Icons.Default.Star,
                    isExpanded = expandedSection == "motivation",
                    onToggle = { expandedSection = if (expandedSection == "motivation") null else "motivation" },
                    primaryColor = Color(0xFF3B82F6)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        var quotesBannersActive by remember {
                            mutableStateOf(sharedPrefs.getBoolean("quotes_active", true))
                        }
                        var morningEncourageActive by remember {
                            mutableStateOf(sharedPrefs.getBoolean("morning_active", true))
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Daily Motto Banners",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Show quote banners under streak flame trackers",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            Switch(
                                checked = quotesBannersActive,
                                onCheckedChange = { isAct ->
                                    quotesBannersActive = isAct
                                    sharedPrefs.edit().putBoolean("quotes_active", isAct).apply()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF3B82F6)
                                )
                            )
                        }
                        
                        Divider(color = MaterialTheme.colorScheme.outlineVariant)
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Morning Encouragement",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Receive push alerts to kickstart the day's goals",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                                )
                            }
                            Switch(
                                checked = morningEncourageActive,
                                onCheckedChange = { isAct ->
                                    morningEncourageActive = isAct
                                    sharedPrefs.edit().putBoolean("morning_active", isAct).apply()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFF3B82F6)
                                )
                            )
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        // Sleek Interactive Quote Showcase box
                        val mantraPairList = remember {
                            Quotes.all.map { q ->
                                Pair(q.english, Pair(q.japanese, q.romaji))
                            }
                        }

                        var currentMantraIdx by remember { mutableStateOf(0) }
                        val activePair = mantraPairList[currentMantraIdx]

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Double Quote Graphic icon
                                Text("“", fontSize = 34.sp, color = Color(0xFF3B82F6), fontWeight = FontWeight.Bold, modifier = Modifier.height(26.dp))

                                Spacer(modifier = Modifier.height(4.dp))

                                if (showJapaneseQuotes) {
                                    Text(
                                        text = activePair.second.first,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = activePair.second.second,
                                        fontSize = 11.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF3B82F6),
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Translation: \"${activePair.first}\"",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                        textAlign = TextAlign.Center
                                    )
                                } else {
                                    Text(
                                        text = activePair.first,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
                                            currentMantraIdx = (currentMantraIdx + 1) % mantraPairList.size
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF131D27),
                                            contentColor = Color.White
                                        ),
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                                        modifier = Modifier.height(30.dp),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Next Mantra 🔮", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 💾 Data Recovery Accordion (NEW)
            item {
                CollapsibleSettingsCard(
                    title = "Data Recovery",
                    icon = Icons.Default.Refresh,
                    isExpanded = expandedSection == "recovery",
                    onToggle = { expandedSection = if (expandedSection == "recovery") null else "recovery" },
                    primaryColor = Color(0xFF06B6D4)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Sandbox database state details representation",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Last Backup:", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(lastBackupDate, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Backup Size:", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(lastBackupSize, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF06B6D4))
                        }
                    }
                }
            }
            
            // 🔄 Backup & Sync Option Accordion
            item {
                CollapsibleSettingsCard(
                    title = "Backup & Sync",
                    icon = Icons.Default.Share,
                    isExpanded = expandedSection == "backup",
                    onToggle = { expandedSection = if (expandedSection == "backup") null else "backup" },
                    primaryColor = Color(0xFF10B981)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = "Preserve and restore your local backup databases securely",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        
                        // JSON Backup options
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "JSON Backup Options",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        exportJsonLauncher.launch("Tsuzuku.json")
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("btn_save_backup_file"),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF10B981),
                                        contentColor = Color(0xFF0F171F)
                                    )
                                ) {
                                    Text("Save File", fontWeight = FontWeight.Bold)
                                }
                                OutlinedButton(
                                    onClick = {
                                        coroutineScope.launch {
                                            val jsonContent = viewModel.exportDataJson()
                                            shareBackup(jsonContent, "Tsuzuku.json")
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("btn_export_json"),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("Share Text", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                            Text(
                                text = "Choose Save File to store on your device, or Share Text to copy/send.",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(2.dp))
                        
                        // JSON Import button
                        Button(
                            onClick = { showImportDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_import_json"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFEAB308),
                                contentColor = Color(0xFF0F171F)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Import / Restore Backup", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // 📊 App Statistics Accordion (NEW)
            item {
                CollapsibleSettingsCard(
                    title = "App Statistics",
                    icon = Icons.Default.List,
                    isExpanded = expandedSection == "stats",
                    onToggle = { expandedSection = if (expandedSection == "stats") null else "stats" },
                    primaryColor = Color(0xFF8B5CF6)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Lifetime tracker progress insights analytics summary",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total Habits", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$totalHabitsCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total Check-ins", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$totalCheckInsCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Days Using Tsuzuku", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$daysUsingCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        Divider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Current Best Streak", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF8B5CF6).copy(alpha = 0.15f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text("🔥 $bestStreakCount Days", fontSize = 12.sp, fontWeight = FontWeight.Black, color = Color(0xFFA78BFA))
                            }
                        }
                    }
                }
            }
            
            // ℹ️ About Tsuzuku Option Accordion
            item {
                CollapsibleSettingsCard(
                    title = "About Tsuzuku",
                    icon = Icons.Default.Info,
                    isExpanded = expandedSection == "about",
                    onToggle = { expandedSection = if (expandedSection == "about") null else "about" },
                    primaryColor = Color(0xFF64748B)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tsuzuku",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Version ${BuildConfig.VERSION_NAME}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                        
                        Spacer(modifier = Modifier.height(14.dp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                                .padding(14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "続けることが、力になる。",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2CB5C3),
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Consistency builds strength.",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(14.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            TextButton(onClick = {
                                showPrivacyDialog = true
                            }) {
                                Text("Privacy Policy", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2CB5C3))
                            }
                            TextButton(onClick = {
                                showTermsDialog = true
                            }) {
                                Text("Terms of Service", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2CB5C3))
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color(0xFF16222F))
                        Spacer(modifier = Modifier.height(14.dp))
                        
                        // Developer footer note
                        Text(
                            text = "Made with ❤️ by Animesh Gupta",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Grid layout of beautifully labelled contact card badges
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    SocialBadge(label = "GitHub", iconRes = R.drawable.ic_github, url = "https://github.com/agupta07505", context = context)
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    SocialBadge(label = "Instagram", iconRes = R.drawable.ic_instagram, url = "https://instagram.com/agupta07505", context = context)
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(modifier = Modifier.weight(1f)) {
                                    SocialBadge(label = "LinkedIn", iconRes = R.drawable.ic_linkedin, url = "https://www.linkedin.com/in/agupta07505", context = context)
                                }
                                Box(modifier = Modifier.weight(1f)) {
                                    SocialBadge(label = "Email", iconRes = R.drawable.ic_email, url = "mailto:agupta07505@gmail.com", isEmail = true, context = context)
                                }
                            }
                        }
                    }
                }
            }
            
            // 🚨 Danger Zone Option Accordion
            item {
                CollapsibleSettingsCardDanger(
                    title = "Danger Zone",
                    icon = Icons.Default.Warning,
                    isExpanded = expandedSection == "danger",
                    onToggle = { expandedSection = if (expandedSection == "danger") null else "danger" }
                ) {
                    Column {
                        Text(
                            text = "Wipes out all stored habits and completed histories locally from this phone's memory.",
                            fontSize = 12.sp,
                            color = Color(0xFFFDA4AF),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        
                        Button(
                            onClick = { showResetDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF43F5E),
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_wipe_all_data"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Delete All Data", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
        
        // Time Picker Custom selection dialog
        if (showTimePickerDialog) {
            AlertDialog(
                onDismissRequest = { showTimePickerDialog = false },
                title = { Text("Set Reminder Time", fontWeight = FontWeight.Bold) },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Choose preferred hour and minute AM/PM for daily notification triggers",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 16.dp),
                            textAlign = TextAlign.Center
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Hour Picker column
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Hour", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEAB308))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { reminderHour = if (reminderHour == 0) 23 else reminderHour - 1 }) {
                                        Text("-", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                                    }
                                    val hrDisplay = if (reminderHour % 12 == 0) 12 else reminderHour % 12
                                    Text(String.format("%02d", hrDisplay), fontSize = 22.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                                    IconButton(onClick = { reminderHour = if (reminderHour == 23) 0 else reminderHour + 1 }) {
                                        Text("+", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                                    }
                                }
                             }

                             Text(":", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)

                             // Minute Picker column
                             Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                 Text("Minute", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEAB308))
                                 Row(verticalAlignment = Alignment.CenterVertically) {
                                     IconButton(onClick = { reminderMinute = if (reminderMinute == 0) 59 else reminderMinute - 5 }) {
                                         Text("-", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                                     }
                                     Text(String.format("%02d", reminderMinute), fontSize = 22.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                                     IconButton(onClick = { reminderMinute = if (reminderMinute >= 55) 0 else reminderMinute + 5 }) {
                                         Text("+", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                                     }
                                 }
                             }

                             // AM/PM Switch Column
                             Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                 Text("Period", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEAB308))
                                 Spacer(modifier = Modifier.height(10.dp))
                                 Button(
                                     onClick = {
                                         reminderHour = if (reminderHour >= 12) reminderHour - 12 else reminderHour + 12
                                     },
                                     colors = ButtonDefaults.buttonColors(
                                         containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                         contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                     ),
                                     shape = RoundedCornerShape(8.dp),
                                     modifier = Modifier.height(34.dp),
                                     contentPadding = PaddingValues(horizontal = 10.dp)
                                 ) {
                                     Text(if (reminderHour >= 12) "PM" else "AM", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                 }
                             }
                        }
                    }
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEAB308),
                            contentColor = Color(0xFF0F171F)
                        ),
                        onClick = {
                            sharedPrefs.edit()
                                .putInt("reminder_hour", reminderHour)
                                .putInt("reminder_minute", reminderMinute)
                                .apply()
                            showTimePickerDialog = false
                            Toast.makeText(context, "Reminder time updated to $formattedReminderTime", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Apply Time", fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showTimePickerDialog = false }) {
                        Text("Nevermind")
                    }
                }
            )
        }
        
        // Import Paste Dialog
        if (showImportDialog) {
            AlertDialog(
                onDismissRequest = { showImportDialog = false },
                title = { Text("Restore / Import Backup") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Locate and upload a previously saved streak_marker_backup.json backup file from your local storage:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Button(
                            onClick = {
                                importJsonFileLauncher.launch("*/*")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_select_backup_file"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Select Backup File (.json)", fontWeight = FontWeight.Bold)
                        }
                        
                        Divider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(vertical = 4.dp))
                        
                        Text(
                            text = "Or, paste the raw text content of your backup schema manually below:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        OutlinedTextField(
                            value = importText,
                            onValueChange = { importText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                                .testTag("import_text_field"),
                            placeholder = { Text("{ \"habits\": [...], \"logs\": [...] }") },
                            textStyle = LocalTextStyle.current.copy(fontSize = 11.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (importText.trim().isNotEmpty()) {
                                viewModel.importDataJson(
                                    importText,
                                    onSuccess = {
                                        showImportDialog = false
                                        importText = ""
                                        Toast.makeText(context, "Backup successfully loaded!", Toast.LENGTH_LONG).show()
                                    },
                                    onError = { err ->
                                        Toast.makeText(context, "Error: $err", Toast.LENGTH_LONG).show()
                                    }
                                )
                            }
                        },
                        enabled = importText.trim().isNotEmpty(),
                        modifier = Modifier.testTag("import_confirm_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF10B981)
                        )
                    ) {
                        Text("Verify & Overwrite Data")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showImportDialog = false }) {
                        Text("Nevermind")
                    }
                }
            )
        }
        
        // Safety Clear verification dialog (Type DELETE to continue)
        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { 
                    showResetDialog = false
                    confirmText = ""
                },
                title = { Text("Delete All Local Habit Data?") },
                text = {
                    Column {
                        Text(
                            text = "This action will permanently wipe all active habits, checklists, and streak logs from this app's memory.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Please write \"DELETE\" (fully capitalized) below to confirm this irreversible destruction:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF43F5E),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = confirmText,
                            onValueChange = { confirmText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("danger_delete_input"),
                            singleLine = true,
                            placeholder = { Text("DELETE") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF43F5E)),
                        enabled = confirmText.trim().uppercase() == "DELETE",
                        onClick = {
                            viewModel.deleteAllLocalData {
                                showResetDialog = false
                                confirmText = ""
                                Toast.makeText(context, "All local data deleted successfully.", Toast.LENGTH_LONG).show()
                            }
                        },
                        modifier = Modifier.testTag("reset_confirm_button")
                    ) {
                        Text("Yes, Permanently Clear")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { 
                        showResetDialog = false
                        confirmText = ""
                    }) {
                        Text("Nevermind")
                    }
                }
            )
        }

        if (showPrivacyDialog) {
            AlertDialog(
                onDismissRequest = { showPrivacyDialog = false },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🛡️ Privacy Policy", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 380.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Last updated: June 22, 2026\n\nYour privacy is extremely important to us. Tsuzuku is designed with a private-by-default, offline-first developer ethos.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text("1. Complete Offline Storage", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "All of your custom habit list definitions, completed check-in calendar logs, and streaks tracking metadata are stored entirely and exclusively inside a private SQLite (Room) database on your physical device.\n\nWe do not operate any web databases, and we never collect, transmit, upload, or sell your habits or tracking history to external servers, cloud services, or third-party developers.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        Text("2. Safe Local Reminders", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "Our daily reminders utilize local Android alarm managers and system notifications directly on your phone. No background tracking SDKs or external push service APIs (like Firebase Cloud Messaging) are active or included in the app.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        Text("3. Zero Telemetry & Ad-Free", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "Tsuzuku features absolutely zero advertising packages, zero Google Analytics packages, and zero user profiling tags. Your daily habit stats are entirely yours and remain private physically in the device memory sandbox.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2CB5C3)),
                        onClick = { showPrivacyDialog = false }
                    ) {
                        Text("I Understand", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }

        if (showTermsDialog) {
            AlertDialog(
                onDismissRequest = { showTermsDialog = false },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("📋 Terms of Service", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 380.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Last updated: June 22, 2026\n\nPlease read these Terms of Service carefully before using our Tsuzuku client application.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text("1. Application License", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "We grant you a personal, non-transferable, and non-exclusive revocable license to run this habit tracking tool solely for your individual self-improvement. Commercial distribution or unauthorized decompilation is prohibited.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        Text("2. Safe Local Database Governance", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "Every record generated in Tsuzuku is stored on your device only. If you clear the application's storage data via Android settings, lose your device, or uninstall the app without capturing a Backup code, you acknowledge that your tracking stats and logs will be permanently deleted and cannot be retrieved by us.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        Text("3. Disclaimer of Warranties", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            text = "Tsuzuku is provided on an 'as-is' and 'as-available' physical utility baseline without diagnostic, accuracy, or completion warranties of any kind. Use of the app is at your own risk.",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2CB5C3)),
                        onClick = { showTermsDialog = false }
                    ) {
                        Text("Accept", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    }
}
