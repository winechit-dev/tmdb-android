package com.tmdb.mobile.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.discover.ui.Discover
import com.tmdb.discover.ui.DiscoverScreen
import com.tmdb.favorites.Favorites
import com.tmdb.favorites.FavoritesScreen
import com.tmdb.search.Search
import com.tmdb.search.SearchScreen

fun NavGraphBuilder.navGraphBuilder(
    navController: NavController
) {

    navDiscover()
    navFavorites()
    navSearch()
}

fun NavGraphBuilder.navDiscover() {
    composable<Discover> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            DiscoverScreen()
        }

    }
}

fun NavGraphBuilder.navFavorites() {
    composable<Favorites> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            FavoritesScreen()
        }

    }
}

fun NavGraphBuilder.navSearch() {
    composable<Search> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            SearchScreen()
        }
    }
}




