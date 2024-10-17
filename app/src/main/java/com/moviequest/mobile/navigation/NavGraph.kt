@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.moviequest.mobile.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.moviequest.designsystem.theme.MovieQuestTheme
import com.moviequest.discover.Discover
import com.moviequest.favorites.Favorites
import kotlin.reflect.KClass

@Composable
fun NavGraph(
    startDestination: Any,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()


    val showBottomNav by remember(backStackEntry) {
        derivedStateOf {
            backStackEntry.hasAnyRoute(
                Discover::class,
                Favorites::class
            )
        }
    }


    MovieQuestTheme {
        Scaffold(
            contentWindowInsets = WindowInsets(0),
            bottomBar = { DefaultBottomNavigation(navController = navController) },
        ) { padding ->
            SharedTransitionLayout {
                CompositionLocalProvider(
                    LocalEntryPadding provides padding,
                    LocalSharedTransitionScope provides this@SharedTransitionLayout
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = startDestination
                        ) {
                            navGraphBuilder(navController)
                        }
                    }
                }
            }
        }
        content()
    }
}

private fun NavBackStackEntry?.hasAnyRoute(vararg routes: KClass<*>): Boolean {
    return routes.any { this?.destination?.hasRoute(it) == true }
}

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
val LocalEntryPadding = compositionLocalOf { PaddingValues() }