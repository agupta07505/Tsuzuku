package com.agupta07505.tsuzuku.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val TsuzukuDarkColorScheme = darkColorScheme(
    primary = Color(0xFF2CB5C3),
    onPrimary = Color(0xFF090E14),
    primaryContainer = Color(0xFF142C34),
    onPrimaryContainer = Color(0xFFACF1FA),
    secondary = Color(0xFF38BDF8),
    onSecondary = Color(0xFF090E14),
    background = Color(0xFF080D12),
    surface = Color(0xFF0E171F),
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF13202E),
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = Color(0xFF2CB5C3),
    outlineVariant = Color(0xFF1E2F40)
)

private val TsuzukuLightColorScheme = lightColorScheme(
    primary = Color(0xFF0A8491),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F7FA),
    onPrimaryContainer = Color(0xFF006064),
    secondary = Color(0xFF0284C7),
    onSecondary = Color.White,
    background = Color(0xFFF8FAFC),
    surface = Color.White,
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFF64748B),
    outline = Color(0xFF0A8491),
    outlineVariant = Color(0xFFCBD5E1)
)

private val AmoledColorScheme = darkColorScheme(
    primary = Color(0xFF2CB5C3),
    onPrimary = Color(0xFF090E14),
    primaryContainer = Color(0xFF142C34),
    onPrimaryContainer = Color(0xFFACF1FA),
    secondary = Color(0xFF38BDF8),
    onSecondary = Color(0xFF090E14),
    background = Color(0xFF000000),
    surface = Color(0xFF000000),
    onBackground = Color(0xFFF8FAFC),
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = Color(0xFF0E171F),
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = Color(0xFF2CB5C3),
    outlineVariant = Color(0xFF16222F)
)

private val TsuzukuGreenColorScheme = darkColorScheme(
    primary = Color(0xFF22C55E),
    onPrimary = Color(0xFF040D08),
    primaryContainer = Color(0xFF0F321B),
    onPrimaryContainer = Color(0xFFDCFCE7),
    secondary = Color(0xFF4ADE80),
    onSecondary = Color(0xFF040D08),
    background = Color(0xFF050E09),
    surface = Color(0xFF0B1B12),
    onBackground = Color(0xFFF0FDF4),
    onSurface = Color(0xFFF0FDF4),
    surfaceVariant = Color(0xFF122C1E),
    onSurfaceVariant = Color(0xFF86EFAC),
    outline = Color(0xFF22C55E),
    outlineVariant = Color(0xFF153A26)
)

private val TsuzukuBlueColorScheme = darkColorScheme(
    primary = Color(0xFF38BDF8),
    onPrimary = Color(0xFF030A14),
    primaryContainer = Color(0xFF0E2D44),
    onPrimaryContainer = Color(0xFFE0F2FE),
    secondary = Color(0xFF7DD3FC),
    onSecondary = Color(0xFF030A14),
    background = Color(0xFF030914),
    surface = Color(0xFF08152B),
    onBackground = Color(0xFFF0F9FF),
    onSurface = Color(0xFFF0F9FF),
    surfaceVariant = Color(0xFF0E223F),
    onSurfaceVariant = Color(0xFFBAE6FD),
    outline = Color(0xFF38BDF8),
    outlineVariant = Color(0xFF13325E)
)

@Composable
fun MyApplicationTheme(
    themePreference: String,
    customAccentColorHex: String = "",
    content: @Composable () -> Unit,
) {
    val darkTheme = isSystemInDarkTheme()
    val baseScheme = when (themePreference) {
        "light" -> TsuzukuLightColorScheme
        "dark" -> TsuzukuDarkColorScheme
        "amoled" -> AmoledColorScheme
        "green" -> TsuzukuGreenColorScheme
        "blue" -> TsuzukuBlueColorScheme
        else -> { // system
            if (darkTheme) TsuzukuDarkColorScheme else TsuzukuLightColorScheme
        }
    }

    val colorScheme = if (customAccentColorHex.isNotEmpty()) {
        try {
            val customColor = Color(android.graphics.Color.parseColor(customAccentColorHex))
            baseScheme.copy(
                primary = customColor,
                outline = customColor,
                primaryContainer = customColor.copy(alpha = 0.15f)
            )
        } catch (e: Exception) {
            baseScheme
        }
    } else {
        baseScheme
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

