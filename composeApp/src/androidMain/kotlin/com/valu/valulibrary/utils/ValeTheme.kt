package com.valu.valulibrary.utils

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primaryAccent,
    secondary = primaryAccent,
    tertiary = primaryAccent
)

private val LightColorScheme = lightColorScheme(
    primary = primaryAccent,
    secondary = primaryAccent,
    tertiary = primaryAccent
)

@Composable
fun ValeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicLightColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun ValeThemeSplash(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val context = LocalView.current

    if (!context.isInEditMode) {
        SideEffect {
            val window = (context.context as Activity).window

            // Esto equivale a <item name="android:statusBarColor">
            window.statusBarColor = Color.Transparent.toArgb() // O tu color primary_secondary

            // Esto equivale a <item name="android:windowLightStatusBar">true</item>
            WindowCompat.getInsetsController(window, context).isAppearanceLightStatusBars = !darkTheme

            // Esto equivale a windowDrawsSystemBarBackgrounds y fitsSystemWindows
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}