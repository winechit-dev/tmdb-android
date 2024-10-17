package com.moviequest.mobile.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moviequest.discover.ui.Discover
import com.moviequest.discover.ui.DiscoverScreen
import com.moviequest.favorites.Favorites
import com.moviequest.favorites.FavoritesScreen
import com.moviequest.search.Search
import com.moviequest.search.SearchScreen

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




