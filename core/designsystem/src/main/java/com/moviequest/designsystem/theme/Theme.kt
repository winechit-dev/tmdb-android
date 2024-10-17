package com.moviequest.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MetallicViolet,
    secondary = Violet,
    surface = EerieBlack,
    secondaryContainer = PhilippinePink,
    onSecondaryContainer = White
)

private val LightColorScheme = lightColorScheme(
    primary = MetallicViolet,
    secondary = Violet,
    secondaryContainer = PhilippinePink,
    onSecondaryContainer = White
)

@Composable
fun MovieQuestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

@Composable
fun AppPreview(
    content: @Composable BoxWithConstraintsScope.() -> Unit,
) {
    MovieQuestTheme {
        Surface {
            BoxWithConstraints {
                content()
            }
        }
    }
}