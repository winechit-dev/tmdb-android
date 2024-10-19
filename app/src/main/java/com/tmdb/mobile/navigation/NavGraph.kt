@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.tmdb.mobile.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tmdb.designsystem.theme.LocalEntryPadding
import com.tmdb.designsystem.theme.LocalSharedTransitionScope
import com.tmdb.designsystem.theme.MovieQuestTheme
import com.tmdb.discover.ui.Discover
import com.tmdb.favorites.Favorites
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

    val offsetY by animateDpAsState(
        label = "",
        targetValue = if (showBottomNav) 0.dp else 150.dp,
        animationSpec = tween(durationMillis = 300)
    )

    MovieQuestTheme {
        Scaffold(
            bottomBar = {
                DefaultBottomNavigation(
                    navController = navController,
                    modifier = Modifier.offset { IntOffset(0, offsetY.roundToPx()) })
            },
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

