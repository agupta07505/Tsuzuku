/*
 * Tsuzuku (2026)
 * (c) Animesh Gupta - github.com/agupta07505
 * Licensed under the MIT License
 */

package com.agupta07505.tsuzuku.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agupta07505.tsuzuku.data.CountdownDateEntity
import com.agupta07505.tsuzuku.ui.CountdownDateViewModel
import com.agupta07505.tsuzuku.ui.theme.MyApplicationTheme
import com.agupta07505.tsuzuku.util.countdownDayInfo
import com.agupta07505.tsuzuku.util.formatCountdownDate

class CountdownDateWidgetConfigureActivity : ComponentActivity() {
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(Activity.RESULT_CANCELED)
        appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        setContent {
            MyApplicationTheme(themePreference = "green") {
                val viewModel: CountdownDateViewModel = viewModel()
                val countdowns by viewModel.countdowns.collectAsState()
                CountdownWidgetConfigureScreen(
                    countdowns = countdowns,
                    onSave = { countdownId ->
                        CountdownDateWidgetProvider.saveCountdownId(this, appWidgetId, countdownId)
                        CountdownDateWidgetProvider.updateAll(this)
                        setResult(Activity.RESULT_OK, android.content.Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId))
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
private fun CountdownWidgetConfigureScreen(
    countdowns: List<CountdownDateEntity>,
    onSave: (Long) -> Unit
) {
    var selectedId by remember(countdowns) { mutableLongStateOf(countdowns.firstOrNull()?.id ?: -1L) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 18.dp),
        contentPadding = PaddingValues(top = 18.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Choose Countdown", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black)
            Text("Select the event this home screen widget should show.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (countdowns.isEmpty()) {
            item {
                Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text("No countdown added", fontWeight = FontWeight.Bold)
                        Text("Create a countdown in Tsuzuku first, then add this widget.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        } else {
            items(countdowns, key = { it.id }) { countdown ->
                val info = countdownDayInfo(countdown.targetDateMillis)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedId = countdown.id },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = selectedId == countdown.id, onClick = { selectedId = countdown.id })
                        Column(Modifier.weight(1f)) {
                            Text(countdown.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text("${info.headline} ${info.label} - ${formatCountdownDate(countdown.targetDateMillis)}", color = Color(countdown.accentColor), maxLines = 1)
                        }
                    }
                }
            }
            item {
                Spacer(Modifier.padding(top = 4.dp))
                Button(onClick = { if (selectedId > 0L) onSave(selectedId) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Add Widget")
                }
            }
        }
    }
}
