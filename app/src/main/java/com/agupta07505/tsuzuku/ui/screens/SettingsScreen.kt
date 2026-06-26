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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.RateReview
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
import java.io.File
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
                    HorizontalDivider(
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
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.2f),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    content()
                }
            }
        }
    }
}

@Composable
private fun SettingsSectionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.colorScheme.primary,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.78f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.28f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(accent.copy(alpha = 0.14f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = accent)
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.22f))
            content()
        }
    }
}

@Composable
private fun SettingsActionRow(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accent: Color = MaterialTheme.colorScheme.primary,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = accent)
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
        }
        trailing?.invoke()
    }
}

@Composable
private fun SettingsSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.28f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun SettingsThemeRow(
    option: ThemeOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(
                if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.24f)
            )
            .border(
                1.dp,
                if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.55f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.16f),
                RoundedCornerShape(16.dp)
            )
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Spacer(Modifier.width(8.dp))
        Text(option.label, modifier = Modifier.weight(1f), fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium)
        ThemePreviewDots(option.colors)
    }
}

@Composable
private fun SettingsInfoPill(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.10f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.22f), RoundedCornerShape(18.dp))
            .padding(14.dp)
    ) {
        Text(value, fontWeight = FontWeight.Black, style = MaterialTheme.typography.titleLarge)
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun SettingsSectionLabel(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text.uppercase(Locale.getDefault()),
        color = color,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(start = 2.dp, bottom = 8.dp)
    )
}

@Composable
private fun SettingsGroupedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.72f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.24f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.10f),
                            Color.Transparent
                        ),
                        radius = 720f
                    )
                )
                .padding(horizontal = 16.dp, vertical = 10.dp),
            content = content
        )
    }
}

@Composable
private fun SettingsMenuDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(start = 60.dp),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.16f)
    )
}

@Composable
private fun SettingsMenuRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color,
    modifier: Modifier = Modifier,
    value: String? = null,
    badge: String? = null,
    chevronColor: Color = iconColor,
    showChevron: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(iconColor.copy(alpha = 0.18f))
                .border(1.dp, iconColor.copy(alpha = 0.25f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(25.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 18.sp
            )
        }
        if (value != null) {
            Text(value, color = chevronColor, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(10.dp))
        }
        if (badge != null) {
            Surface(
                shape = RoundedCornerShape(50),
                color = chevronColor.copy(alpha = 0.16f),
                border = BorderStroke(1.dp, chevronColor.copy(alpha = 0.18f))
            ) {
                Text(
                    badge,
                    color = chevronColor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
        }
        if (showChevron) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = chevronColor,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun SettingsBrandFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Made with ❤️ by Animesh Gupta",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MissionMountainIllustration(modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // 1. Soft glowing sun/moon in the background
        drawCircle(
            color = primaryColor.copy(alpha = 0.12f),
            radius = w * 0.24f,
            center = androidx.compose.ui.geometry.Offset(w * 0.70f, h * 0.35f)
        )

        // 2. Minimal distant background mountain peak
        val bgHillPath = Path().apply {
            moveTo(w * 0.05f, h * 0.90f)
            lineTo(w * 0.40f, h * 0.38f)
            lineTo(w * 0.75f, h * 0.90f)
            close()
        }
        val bgGradient = Brush.verticalGradient(
            colors = listOf(
                secondaryColor.copy(alpha = 0.30f),
                secondaryColor.copy(alpha = 0.02f)
            ),
            startY = h * 0.38f,
            endY = h * 0.90f
        )
        drawPath(bgHillPath, bgGradient)

        // 3. Foreground mountain peak
        val fgHillPath = Path().apply {
            moveTo(w * 0.25f, h * 0.90f)
            lineTo(w * 0.65f, h * 0.25f)
            lineTo(w * 0.98f, h * 0.90f)
            close()
        }
        val fgGradient = Brush.verticalGradient(
            colors = listOf(
                primaryColor.copy(alpha = 0.55f),
                primaryColor.copy(alpha = 0.04f)
            ),
            startY = h * 0.25f,
            endY = h * 0.90f
        )
        drawPath(fgHillPath, fgGradient)

        // 4. Subtle, minimal clean accent peak outline (drawn on the foreground peak)
        val fgPeakOutline = Path().apply {
            moveTo(w * 0.55f, h * 0.41f)
            lineTo(w * 0.65f, h * 0.25f)
            lineTo(w * 0.75f, h * 0.41f)
        }
        drawPath(
            path = fgPeakOutline,
            color = primaryColor.copy(alpha = 0.75f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = 2.dp.toPx(),
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )

        // 5. Minimal clean accent peak outline for bg peak
        val bgPeakOutline = Path().apply {
            moveTo(w * 0.32f, h * 0.50f)
            lineTo(w * 0.40f, h * 0.38f)
            lineTo(w * 0.48f, h * 0.50f)
        }
        drawPath(
            path = bgPeakOutline,
            color = secondaryColor.copy(alpha = 0.40f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(
                width = 1.5.dp.toPx(),
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )
    }
}

@Composable
private fun AboutContactButton(
    title: String,
    subtitle: String,
    @DrawableRes iconResId: Int,
    iconColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .heightIn(min = 78.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.28f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.22f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun AboutTsuzukuScreen(
    versionName: String,
    onBack: () -> Unit,
    onPrivacy: () -> Unit,
    onTerms: () -> Unit,
    onOpenSource: () -> Unit,
    onGitHub: () -> Unit,
    onInstagram: () -> Unit,
    onLinkedIn: () -> Unit,
    onEmail: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.20f),
                        Color.Transparent,
                        Color.Transparent
                    ),
                    center = androidx.compose.ui.geometry.Offset(180f, 180f),
                    radius = 980f
                )
            )
            .padding(horizontal = 18.dp),
        contentPadding = PaddingValues(top = 22.dp, bottom = 116.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.offset(x = (-12).dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = "About Tsuzuku",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.offset(x = (-4).dp)
                    )
                }
                Text(
                    text = "Learn more about the app and its mission.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 44.dp, top = 2.dp)
                )
            }
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tsuzuku_create_habbit_logo),
                    contentDescription = "Tsuzuku",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.30f), CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(12.dp))
                Text("Tsuzuku", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black)
                Text("続く — Keep going, every day.", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(14.dp))
                Text(
                    "Tsuzuku is a privacy-first habit tracker and focus companion.\nAll your data stays on your device. Always.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 18.sp
                )
            }
        }

        item {
            SettingsGroupedCard {
                SettingsMenuRow(
                    icon = Icons.Default.Info,
                    title = "Version",
                    subtitle = versionName,
                    iconColor = Color(0xFF4ADE80),
                    badge = "Latest",
                    showChevron = false,
                    onClick = {}
                )
                SettingsMenuDivider()
                SettingsMenuRow(
                    icon = Icons.Default.CloudOff,
                    title = "Privacy",
                    subtitle = "100% Offline. Your data never leaves your device.",
                    iconColor = Color(0xFF38BDF8),
                    onClick = onPrivacy
                )
                SettingsMenuDivider()
                SettingsMenuRow(
                    icon = Icons.Default.Gavel,
                    title = "Terms of Use",
                    subtitle = "Read the terms and conditions for using Tsuzuku.",
                    iconColor = Color(0xFFFACC15),
                    chevronColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    onClick = onTerms
                )
                SettingsMenuDivider()
                SettingsMenuRow(
                    icon = Icons.Default.PrivacyTip,
                    title = "Privacy Policy",
                    subtitle = "Learn how we protect your privacy.",
                    iconColor = Color(0xFFC084FC),
                    chevronColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    onClick = onPrivacy
                )
                SettingsMenuDivider()
                SettingsMenuRow(
                    icon = Icons.Default.Code,
                    title = "Open Source",
                    subtitle = "Tsuzuku is open source and community driven.",
                    iconColor = Color(0xFF22C55E),
                    chevronColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    showChevron = false,
                    onClick = onOpenSource
                )
            }
        }

        item {
            SettingsGroupedCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(Modifier.width(12.dp))
                            Text("Our Mission", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                        }
                        Spacer(Modifier.height(14.dp))
                        Text(
                            "To help you build better habits, stay focused, and live intentionally.\nNo ads. No tracking. Just you and your journey.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 20.sp
                        )
                    }
                    MissionMountainIllustration(
                        modifier = Modifier
                            .size(width = 132.dp, height = 108.dp)
                            .padding(start = 12.dp)
                    )
                }
            }
        }

        item {
            SettingsGroupedCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFB7185).copy(alpha = 0.12f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFFB7185), modifier = Modifier.size(16.dp))
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                "MADE WITH LOVE BY",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelSmall,
                                letterSpacing = 1.sp,
                                color = Color(0xFFFB7185)
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("Animesh Gupta", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                        Spacer(Modifier.height(4.dp))
                        Text("Developer • Designer • Lifelong Learner", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.labelMedium)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.developer),
                        contentDescription = "Animesh Gupta",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Black, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        item {
            SettingsGroupedCard {
                Text("Connect with me", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                        AboutContactButton(
                            title = "GitHub",
                            subtitle = "@agupta07505",
                            iconResId = R.drawable.ic_github,
                            iconColor = MaterialTheme.colorScheme.onSurface,
                            onClick = onGitHub,
                            modifier = Modifier.weight(1f)
                        )
                        AboutContactButton(
                            title = "Instagram",
                            subtitle = "@agupta07505",
                            iconResId = R.drawable.ic_instagram,
                            iconColor = Color(0xFFE1306C),
                            onClick = onInstagram,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                        AboutContactButton(
                            title = "LinkedIn",
                            subtitle = "/in/agupta07505",
                            iconResId = R.drawable.ic_linkedin,
                            iconColor = Color(0xFF0A66C2),
                            onClick = onLinkedIn,
                            modifier = Modifier.weight(1f)
                        )
                        AboutContactButton(
                            title = "Email",
                            subtitle = "agupta07505@gmail.com",
                            iconResId = R.drawable.ic_email,
                            iconColor = Color(0xFFEA4335),
                            onClick = onEmail,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        item {
            SettingsGroupedCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CloudOff, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(14.dp))
                    Text(
                        "Tsuzuku is and always will be ad-free and analytics-free.\nYour trust means everything.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0L) return "0 B"
    val units = listOf("B", "KB", "MB", "GB")
    var value = bytes.toDouble()
    var unitIndex = 0
    while (value >= 1024 && unitIndex < units.lastIndex) {
        value /= 1024
        unitIndex++
    }
    return if (unitIndex == 0) "${value.toLong()} ${units[unitIndex]}" else String.format(Locale.US, "%.1f %s", value, units[unitIndex])
}

private fun fileSizeSafely(file: File): Long = runCatching {
    if (!file.exists()) 0L else if (file.isFile) file.length() else file.listFiles().orEmpty().sumOf { fileSizeSafely(it) }
}.getOrDefault(0L)

private fun databaseSizeBytes(context: Context): Long {
    val db = context.getDatabasePath("streak_marker_db")
    val parent = db.parentFile
    return listOf(
        db,
        File(parent, "streak_marker_db-wal"),
        File(parent, "streak_marker_db-shm")
    ).sumOf { fileSizeSafely(it) }
}

private fun appStorageBytes(context: Context): Long {
    return fileSizeSafely(File(context.applicationInfo.dataDir))
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
    var remindersEnabled by remember {
        mutableStateOf(sharedPrefs.getBoolean("reminders_active", true))
    }
    var permanentNotificationEnabled by remember {
        mutableStateOf(sharedPrefs.getBoolean("permanent_notification_active", false))
    }


    // Dialog state
    var showImportDialog by remember { mutableStateOf(false) }
    
    var showResetDialog by remember { mutableStateOf(false) }
    var showResetFocusDialog by remember { mutableStateOf(false) }
    var confirmText by remember { mutableStateOf("") }

    var showTimePickerDialog by remember { mutableStateOf(false) }

    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }
    var showAppearanceDialog by remember { mutableStateOf(false) }
    var showNotificationDialog by remember { mutableStateOf(false) }
    var showPersonalizationDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

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
    val focusSessionsList by viewModel.focusSessions.collectAsState()

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
        mutableStateOf(sharedPrefs.getString("last_backup_date", "Never") ?: "Never")
    }
    var lastBackupSize by remember {
        mutableStateOf(sharedPrefs.getString("last_backup_size", "0 B") ?: "0 B")
    }

    fun openUrl(url: String, fallbackMessage: String) {
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }.onFailure {
            Toast.makeText(context, fallbackMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun sendMail(subject: String, body: String) {
        runCatching {
            context.startActivity(
                Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:agupta07505@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, body)
                }
            )
        }.onFailure {
            Toast.makeText(context, "No email app found.", Toast.LENGTH_SHORT).show()
        }
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
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.background
                    ),
                    center = androidx.compose.ui.geometry.Offset(80f, 120f),
                    radius = 980f
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
                .testTag("settings_scroll"),
            contentPadding = PaddingValues(top = 22.dp, bottom = 116.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Customize your experience and manage your local data.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            item {
                Column {
                    SettingsSectionLabel("Preferences")
                    SettingsGroupedCard {
                        SettingsMenuRow(
                            icon = Icons.Default.Edit,
                            title = "Appearance",
                            subtitle = "Theme, colors and display options",
                            iconColor = Color(0xFF4ADE80),
                            onClick = { showAppearanceDialog = true }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Notifications,
                            title = "Notifications",
                            subtitle = "Reminders, alerts and focus notifications",
                            iconColor = Color(0xFFFACC15),
                            onClick = { showNotificationDialog = true }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Settings,
                            title = "Personalization",
                            subtitle = "Home cards, quotes, celebrations and more",
                            iconColor = Color(0xFF22C55E),
                            onClick = { showPersonalizationDialog = true }
                        )
                    }
                }
            }

            item {
                Column {
                    SettingsSectionLabel("Local Data")
                    SettingsGroupedCard {
                        SettingsMenuRow(
                            icon = Icons.Default.FileUpload,
                            title = "Export Data (.json)",
                            subtitle = "Export all habits, focus sessions and settings to a JSON file",
                            iconColor = Color(0xFFC084FC),
                            chevronColor = Color(0xFFC084FC),
                            showChevron = false,
                            onClick = { exportJsonLauncher.launch("Tsuzuku.json") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.FileDownload,
                            title = "Import Data (.json)",
                            subtitle = "Restore data from a previously exported JSON file",
                            iconColor = Color(0xFF38BDF8),
                            chevronColor = Color(0xFF38BDF8),
                            onClick = { showImportDialog = true }
                        )
                    }
                }
            }

            item {
                Column {
                    SettingsSectionLabel("Community")
                    SettingsGroupedCard {
                        SettingsMenuRow(
                            icon = Icons.Default.Star,
                            title = "Star on GitHub",
                            subtitle = "Support the project by starring on GitHub",
                            iconColor = Color(0xFFE5E7EB),
                            chevronColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            showChevron = false,
                            onClick = { openUrl("https://github.com/agupta07505/Tsuzuku", "Cannot open GitHub.") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Lightbulb,
                            title = "Request a Feature",
                            subtitle = "Suggest a new feature or improvement",
                            iconColor = Color(0xFFFACC15),
                            chevronColor = Color(0xFFFACC15),
                            showChevron = false,
                            onClick = { openUrl("https://github.com/agupta07505/Tsuzuku/issues/new?template=feature_request.md", "Cannot open feature request page.") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.BugReport,
                            title = "Report Bugs",
                            subtitle = "Found an issue? Let us know",
                            iconColor = Color(0xFFEF4444),
                            chevronColor = Color(0xFFEF4444),
                            showChevron = false,
                            onClick = { openUrl("https://github.com/agupta07505/Tsuzuku/issues/new?template=bug_report.md", "Cannot open bug report page.") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.RateReview,
                            title = "App Review",
                            subtitle = "Share feedback and review Tsuzuku",
                            iconColor = Color(0xFF4ADE80),
                            chevronColor = Color(0xFF4ADE80),
                            showChevron = false,
                            onClick = { openUrl("https://github.com/agupta07505/Tsuzuku/issues/new?template=app_review.md", "Cannot open app review page.") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Groups,
                            title = "Join Community",
                            subtitle = "Join the discussion and share your journey",
                            iconColor = Color(0xFFC084FC),
                            chevronColor = Color(0xFFC084FC),
                            badge = "Coming Soon",
                            showChevron = false,
                            onClick = { Toast.makeText(context, "Community is coming soon.", Toast.LENGTH_SHORT).show() }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Gavel,
                            title = "MIT License",
                            subtitle = "Open source and free to use",
                            iconColor = Color(0xFF2DD4BF),
                            chevronColor = Color(0xFF2DD4BF),
                            showChevron = false,
                            onClick = { openUrl("https://github.com/agupta07505/Tsuzuku/blob/main/LICENSE", "Cannot open license.") }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Info,
                            title = "About Tsuzuku",
                            subtitle = "Version, developer and app information",
                            iconColor = Color(0xFF38BDF8),
                            chevronColor = Color(0xFF38BDF8),
                            value = "v${BuildConfig.VERSION_NAME}",
                            onClick = { showAboutDialog = true }
                        )
                    }
                }
            }

            item {
                Column {
                    SettingsSectionLabel("Danger Zone", color = MaterialTheme.colorScheme.error)
                    SettingsGroupedCard {
                        SettingsMenuRow(
                            icon = Icons.AutoMirrored.Filled.List,
                            title = "Reset Focus Statistics",
                            subtitle = "Reset all focus sessions and statistics",
                            iconColor = Color(0xFFEF4444),
                            chevronColor = Color(0xFFEF4444),
                            onClick = { showResetFocusDialog = true }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Delete,
                            title = "Delete All Habits",
                            subtitle = "Permanently delete all habits and progress",
                            iconColor = Color(0xFFEF4444),
                            chevronColor = Color(0xFFEF4444),
                            onClick = { showResetDialog = true }
                        )
                        SettingsMenuDivider()
                        SettingsMenuRow(
                            icon = Icons.Default.Warning,
                            title = "Factory Reset Tsuzuku",
                            subtitle = "Erase everything and start fresh",
                            iconColor = Color(0xFFEF4444),
                            chevronColor = Color(0xFFEF4444),
                            onClick = { showResetDialog = true }
                        )
                    }
                }
            }

            item {
                SettingsBrandFooter()
            }
        }

        if (false) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("settings_scroll"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Keep Tsuzuku calm, private, and synced with your workflow.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.28f))
                ) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Local-first setup", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    "Your habits, check-ins, and Focus history stay on this device unless you export them.",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                            SettingsInfoPill("Habits", totalHabitsCount.toString(), Modifier.weight(1f))
                            SettingsInfoPill("Check-ins", totalCheckInsCount.toString(), Modifier.weight(1f))
                        }
                    }
                }
            }

            item {
                SettingsSectionCard(
                    title = "Appearance",
                    subtitle = "Theme and accent color",
                    icon = Icons.Default.Edit
                ) {
                    val themeOptions = listOf(
                        ThemeOption("system", "System", listOf(Color(0xFFF8FAFC), Color(0xFF0F171F), Color(0xFF94A3B8))),
                        ThemeOption("light", "Light", listOf(Color(0xFFE2E8F0), Color(0xFFF8FAFC), Color(0xFF2CB5C3))),
                        ThemeOption("dark", "Dark", listOf(Color(0xFF090E14), Color(0xFF16222F), Color(0xFF2CB5C3))),
                        ThemeOption("amoled", "AMOLED", listOf(Color(0xFF000000), Color(0xFF090E14), Color(0xFF2CB5C3))),
                        ThemeOption("green", "Tsuzuku Green", listOf(Color(0xFF050E09), Color(0xFF122C1E), Color(0xFF22C55E))),
                        ThemeOption("blue", "Tsuzuku Blue", listOf(Color(0xFF030914), Color(0xFF0E223F), Color(0xFF38BDF8)))
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        themeOptions.forEach { option ->
                            SettingsThemeRow(
                                option = option,
                                selected = themeState == option.key,
                                onClick = { onThemeChanged(option.key) }
                            )
                        }
                        OutlinedTextField(
                            value = customAccentColorHex,
                            onValueChange = onAccentColorChanged,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text("Custom accent hex") },
                            placeholder = { Text("#22C55E") },
                            supportingText = { Text("Leave empty to use the selected theme accent.") },
                            trailingIcon = {
                                if (customAccentColorHex.isNotBlank()) {
                                    IconButton(onClick = { onAccentColorChanged("") }) {
                                        Icon(Icons.Default.Refresh, contentDescription = "Reset accent")
                                    }
                                }
                            }
                        )
                    }
                }
            }

            item {
                SettingsSectionCard(
                    title = "Notifications",
                    subtitle = "Habit reminders only",
                    icon = Icons.Default.Notifications,
                    accent = Color(0xFFEAB308)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        SettingsSwitchRow(
                            title = "Daily Habit Reminders",
                            subtitle = "Use local notifications to remind you about active habits.",
                            checked = remindersEnabled,
                            onCheckedChange = { enabled ->
                                remindersEnabled = enabled
                                sharedPrefs.edit().putBoolean("reminders_active", enabled).apply()
                            }
                        )
                        SettingsActionRow(
                            title = "Reminder Time",
                            subtitle = formattedReminderTime,
                            icon = Icons.Default.Notifications,
                            onClick = { showTimePickerDialog = true },
                            accent = Color(0xFFEAB308),
                            trailing = { Text("Change", color = Color(0xFFEAB308), fontWeight = FontWeight.Bold) }
                        )
                        SettingsSwitchRow(
                            title = "Status Bar Motivation",
                            subtitle = "Show a quiet ongoing motivational notification.",
                            checked = permanentNotificationEnabled,
                            onCheckedChange = { enabled ->
                                permanentNotificationEnabled = enabled
                                sharedPrefs.edit().putBoolean("permanent_notification_active", enabled).apply()
                                if (enabled) {
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.updatePermanentNotification(context)
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.scheduleNextHourlyUpdate(context)
                                } else {
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.cancelPermanentNotification(context)
                                }
                            }
                        )
                    }
                }
            }

            item {
                SettingsSectionCard(
                    title = "Data Backup",
                    subtitle = "Export or restore your local data",
                    icon = Icons.Default.Share,
                    accent = Color(0xFF10B981)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            "Includes habits, check-ins, and Focus history.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = { exportJsonLauncher.launch("Tsuzuku.json") },
                                modifier = Modifier.weight(1f).testTag("btn_save_backup_file"),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("Save JSON", fontWeight = FontWeight.Bold)
                            }
                            OutlinedButton(
                                onClick = {
                                    coroutineScope.launch {
                                        val jsonContent = viewModel.exportDataJson()
                                        shareBackup(jsonContent, "Tsuzuku.json")
                                    }
                                },
                                modifier = Modifier.weight(1f).testTag("btn_export_json"),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("Share")
                            }
                        }
                        Button(
                            onClick = { showImportDialog = true },
                            modifier = Modifier.fillMaxWidth().testTag("btn_import_json"),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEAB308), contentColor = Color(0xFF0F171F)),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Import / Restore Backup", fontWeight = FontWeight.Bold)
                        }
                        Text(
                            "Last backup: $lastBackupDate • $lastBackupSize",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {
                SettingsSectionCard(
                    title = "Privacy & App",
                    subtitle = "Offline-first details",
                    icon = Icons.Default.Info,
                    accent = Color(0xFF64748B)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Tsuzuku ${BuildConfig.VERSION_NAME}", fontWeight = FontWeight.Bold)
                        Text(
                            "No ads. No telemetry. Your tracking data lives in the local Room database.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                            OutlinedButton(onClick = { showPrivacyDialog = true }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp)) {
                                Text("Privacy")
                            }
                            OutlinedButton(onClick = { showTermsDialog = true }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(14.dp)) {
                                Text("Terms")
                            }
                        }
                    }
                }
            }

            item {
                SettingsSectionCard(
                    title = "Danger Zone",
                    subtitle = "Permanent local data actions",
                    icon = Icons.Default.Warning,
                    accent = MaterialTheme.colorScheme.error
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            "Deletes habits, check-ins, streak logs, and stored app data from this device.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Button(
                            onClick = { showResetDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error, contentColor = MaterialTheme.colorScheme.onError),
                            modifier = Modifier.fillMaxWidth().testTag("btn_wipe_all_data"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Delete All Data", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
        }

        val showLegacySettings = remember { false }
        if (showLegacySettings) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("settings_scroll"),
            contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Screen Header Info
            item {
                Column {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.headlineMedium,
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

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

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

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

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
                        
                        HorizontalDivider(color = Color(0xFF16222F))
                        
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
                        
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        
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
                        
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        
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

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

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
                            text = "Preserve and restore habits, check-ins, and Focus sessions securely",
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
                            text = "Includes habits, check-ins, and Focus history. Save locally or share as text.",
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
                    icon = Icons.AutoMirrored.Filled.List,
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

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Total Check-ins", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$totalCheckInsCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Days Using Tsuzuku", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$daysUsingCount", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

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
                        HorizontalDivider(color = Color(0xFF16222F))
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
        }

        if (showAppearanceDialog) {
            AlertDialog(
                onDismissRequest = { showAppearanceDialog = false },
                title = { Text("Appearance", fontWeight = FontWeight.Bold) },
                text = {
                    val themeOptions = listOf(
                        ThemeOption("system", "System", listOf(Color(0xFFF8FAFC), Color(0xFF0F171F), Color(0xFF94A3B8))),
                        ThemeOption("light", "Light", listOf(Color(0xFFE2E8F0), Color(0xFFF8FAFC), Color(0xFF2CB5C3))),
                        ThemeOption("dark", "Dark", listOf(Color(0xFF090E14), Color(0xFF16222F), Color(0xFF2CB5C3))),
                        ThemeOption("amoled", "AMOLED", listOf(Color(0xFF000000), Color(0xFF090E14), Color(0xFF2CB5C3))),
                        ThemeOption("green", "Tsuzuku Green", listOf(Color(0xFF050E09), Color(0xFF122C1E), Color(0xFF22C55E))),
                        ThemeOption("blue", "Tsuzuku Blue", listOf(Color(0xFF030914), Color(0xFF0E223F), Color(0xFF38BDF8)))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 460.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        themeOptions.forEach { option ->
                            SettingsThemeRow(
                                option = option,
                                selected = themeState == option.key,
                                onClick = { onThemeChanged(option.key) }
                            )
                        }
                        OutlinedTextField(
                            value = customAccentColorHex,
                            onValueChange = onAccentColorChanged,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text("Custom accent hex") },
                            placeholder = { Text("#22C55E") },
                            supportingText = { Text("Leave empty to use the selected theme accent.") },
                            trailingIcon = {
                                if (customAccentColorHex.isNotBlank()) {
                                    IconButton(onClick = { onAccentColorChanged("") }) {
                                        Icon(Icons.Default.Refresh, contentDescription = "Reset accent")
                                    }
                                }
                            }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showAppearanceDialog = false }) { Text("Done") }
                }
            )
        }

        if (showNotificationDialog) {
            AlertDialog(
                onDismissRequest = { showNotificationDialog = false },
                title = { Text("Notifications", fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        SettingsSwitchRow(
                            title = "Daily Habit Reminders",
                            subtitle = "Use local notifications to remind you about active habits.",
                            checked = remindersEnabled,
                            onCheckedChange = { enabled ->
                                remindersEnabled = enabled
                                sharedPrefs.edit().putBoolean("reminders_active", enabled).apply()
                            }
                        )
                        SettingsActionRow(
                            title = "Reminder Time",
                            subtitle = formattedReminderTime,
                            icon = Icons.Default.Notifications,
                            onClick = { showTimePickerDialog = true },
                            accent = Color(0xFFEAB308),
                            trailing = { Text("Change", color = Color(0xFFEAB308), fontWeight = FontWeight.Bold) }
                        )
                        SettingsSwitchRow(
                            title = "Status Bar Motivation",
                            subtitle = "Show a quiet ongoing motivational notification.",
                            checked = permanentNotificationEnabled,
                            onCheckedChange = { enabled ->
                                permanentNotificationEnabled = enabled
                                sharedPrefs.edit().putBoolean("permanent_notification_active", enabled).apply()
                                if (enabled) {
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.updatePermanentNotification(context)
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.scheduleNextHourlyUpdate(context)
                                } else {
                                    com.agupta07505.tsuzuku.notification.HabitNotificationHelper.cancelPermanentNotification(context)
                                }
                            }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showNotificationDialog = false }) { Text("Done") }
                }
            )
        }

        if (showPersonalizationDialog) {
            var confettiEnabled by remember { mutableStateOf(sharedPrefs.getBoolean("confetti_active", true)) }
            var quoteBannersEnabled by remember { mutableStateOf(sharedPrefs.getBoolean("quotes_active", true)) }
            var morningEncouragementEnabled by remember { mutableStateOf(sharedPrefs.getBoolean("morning_active", true)) }
            AlertDialog(
                onDismissRequest = { showPersonalizationDialog = false },
                title = { Text("Personalization", fontWeight = FontWeight.Bold) },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 420.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        SettingsSwitchRow(
                            title = "Confetti Celebrations",
                            subtitle = "Celebrate when completing today's habits.",
                            checked = confettiEnabled,
                            onCheckedChange = { enabled ->
                                confettiEnabled = enabled
                                sharedPrefs.edit().putBoolean("confetti_active", enabled).apply()
                            }
                        )
                        SettingsSwitchRow(
                            title = "Japanese Motto",
                            subtitle = "Show the Japanese consistency motto where supported.",
                            checked = showJapaneseQuotes,
                            onCheckedChange = { enabled ->
                                showJapaneseQuotes = enabled
                                sharedPrefs.edit().putBoolean("show_japanese_quotes", enabled).apply()
                            }
                        )
                        SettingsSwitchRow(
                            title = "Daily Quote Banners",
                            subtitle = "Show motivational quotes around habit surfaces.",
                            checked = quoteBannersEnabled,
                            onCheckedChange = { enabled ->
                                quoteBannersEnabled = enabled
                                sharedPrefs.edit().putBoolean("quotes_active", enabled).apply()
                            }
                        )
                        SettingsSwitchRow(
                            title = "Morning Encouragement",
                            subtitle = "Show stronger encouragement at the start of the day.",
                            checked = morningEncouragementEnabled,
                            onCheckedChange = { enabled ->
                                morningEncouragementEnabled = enabled
                                sharedPrefs.edit().putBoolean("morning_active", enabled).apply()
                            }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showPersonalizationDialog = false }) { Text("Done") }
                }
            )
        }

        if (showAboutDialog) {
            AboutTsuzukuScreen(
                versionName = BuildConfig.VERSION_NAME,
                onBack = { showAboutDialog = false },
                onPrivacy = { showPrivacyDialog = true },
                onTerms = { showTermsDialog = true },
                onOpenSource = { openUrl("https://github.com/agupta07505/Tsuzuku", "Cannot open GitHub.") },
                onGitHub = { openUrl("https://github.com/agupta07505", "Cannot open GitHub.") },
                onInstagram = { openUrl("https://instagram.com/agupta07505", "Cannot open Instagram.") },
                onLinkedIn = { openUrl("https://www.linkedin.com/in/agupta07505", "Cannot open LinkedIn.") },
                onEmail = { sendMail("Tsuzuku", "") },
                modifier = Modifier.matchParentSize()
            )
        }

        if (showResetFocusDialog) {
            AlertDialog(
                onDismissRequest = { showResetFocusDialog = false },
                title = { Text("Reset Focus Statistics?") },
                text = {
                    Text("This will permanently delete all saved Focus sessions and Focus statistics. Habits will not be deleted.")
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        onClick = {
                            viewModel.deleteAllFocusData {
                                showResetFocusDialog = false
                                Toast.makeText(context, "Focus statistics reset.", Toast.LENGTH_LONG).show()
                            }
                        }
                    ) {
                        Text("Reset Focus")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showResetFocusDialog = false }) { Text("Cancel") }
                }
            )
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
                            text = "Select a Tsuzuku JSON backup containing habits, check-ins, and Focus sessions.",
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
                        Text(
                            text = "Import will replace the current local data after the file is read successfully.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showImportDialog = false }) {
                        Text("Close")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { importJsonFileLauncher.launch("*/*") }) { Text("Choose File") }
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
