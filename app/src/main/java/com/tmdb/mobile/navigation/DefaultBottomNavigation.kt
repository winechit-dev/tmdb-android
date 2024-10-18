package com.tmdb.mobile.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tmdb.designsystem.theme.AppPreviewWrapper
import com.tmdb.designsystem.theme.ThemePreviews
import com.tmdb.discover.ui.Discover
import com.tmdb.discover.ui.SurfaceContainerAlpha
import com.tmdb.favorites.Favorites
import com.tmdb.designsystem.R
import kotlin.reflect.KClass

enum class DefaultBottomBarNavigationItem(
    val route: Any,
    val label: String,
    @DrawableRes val icon: Int
) {
    DiscoverItem(
        Discover,
        "Discover",
        R.drawable.ic_discover
    ),

    FavoritesItem(
        Favorites,
        "Favorites",
        R.drawable.ic_favorites
    )
}

private fun NavBackStackEntry?.isRouteSelected(route: KClass<*>): Boolean {
    return this?.destination?.hasRoute(route) == true
}

@Composable
fun DefaultBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val bottomBarPadding = WindowInsets.navigationBars
        .asPaddingValues(LocalDensity.current)
        .calculateBottomPadding() / 2

    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets(bottom = bottomBarPadding),
        containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = SurfaceContainerAlpha)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        DefaultBottomBarNavigationItem.entries
            .forEach { item ->
                NavigationBarItem(
                    selected = navBackStackEntry.isRouteSelected(item.route::class),
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.name
                        )
                    },
                    label = {
                        Text(
                            text = item.label
                        )
                    }
                )
            }
    }
}

@ThemePreviews
@Composable
private fun BottomNavigationPreview() {
    AppPreviewWrapper {
        DefaultBottomNavigation(
            navController = rememberNavController()
        )
    }
}