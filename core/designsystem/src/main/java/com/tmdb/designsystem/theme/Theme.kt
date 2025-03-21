@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.designsystem.theme

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorScheme = darkColorScheme(
    primary = MetallicViolet,
    onPrimary = White,
    secondary = Violet,
    surface = EerieBlack,
    tertiary = PhilippinePink,
    onTertiary = White
)

private val LightColorScheme = lightColorScheme(
    primary = MetallicViolet,
    onPrimary = White,
    secondary = Violet,
    tertiary = PhilippinePink,
    onTertiary = White
)

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S


@Composable
fun MovieQuestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && supportsDynamicTheming() -> {
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews


@Composable
fun AppPreviewWrapper(
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

@Composable
fun AppPreviewWithSharedTransitionLayout(
    content: @Composable BoxWithConstraintsScope.() -> Unit,
) {
    MovieQuestTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this
                ) {
                    Surface {
                        BoxWithConstraints {
                            content()
                        }
                    }
                }
            }
        }
    }
}

val LocalEntryPadding = compositionLocalOf { PaddingValues() }
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }