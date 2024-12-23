package com.tmdb.mobile.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.tmdb.designsystem.theme.LocalNavAnimatedVisibilityScope
import com.tmdb.discover.ui.Discover
import com.tmdb.discover.ui.DiscoverEvent
import com.tmdb.discover.ui.DiscoverScreen
import com.tmdb.discover.ui.details.MovieDetails
import com.tmdb.discover.ui.details.MovieDetailsEvent
import com.tmdb.discover.ui.details.MovieDetailsScreen
import com.tmdb.favorites.Favorites
import com.tmdb.favorites.FavoritesScreen
import com.tmdb.search.Search
import com.tmdb.search.SearchEvent
import com.tmdb.search.SearchScreen
import com.tmdb.settings.Settings
import com.tmdb.settings.SettingsScreen

fun NavGraphBuilder.navGraphBuilder(
    navController: NavController
) {

    navDiscover(navController)
    navFavorites(navController)
    navSearch(navController)
    navMovieDetails(navController)
    navSettings()
}

fun NavGraphBuilder.navDiscover(navController: NavController) {
    composable<Discover> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            DiscoverScreen(
                onEvent = { event ->
                    when (event) {
                        is DiscoverEvent.MovieDetails -> {
                            navController.navigate(
                                MovieDetails(
                                    id = event.model.id,
                                    name = event.model.name,
                                    posterPath = event.model.posterPath,
                                    type = event.type
                                )
                            )
                        }

                        is DiscoverEvent.Search -> {
                            navController.navigate(Search)
                        }

                        else -> Unit
                    }

                }
            )
        }

    }
}

fun NavGraphBuilder.navFavorites(
    navController: NavController
) {
    composable<Favorites> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            FavoritesScreen(
                onNavigateMovieDetails = { model, type ->
                    navController.navigate(
                        MovieDetails(
                            id = model.movieId,
                            name = model.name,
                            posterPath = model.posterPath,
                            type = type
                        )
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.navSearch(
    navController: NavController
) {
    composable<Search> {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            SearchScreen(
                onEvent = { event ->
                    when (event) {
                        is SearchEvent.MovieDetails -> {
                            navController.navigate(
                                MovieDetails(
                                    id = event.model.id,
                                    name = event.model.name,
                                    posterPath = event.model.posterPath,
                                    type = ""
                                )
                            )
                        }

                        is SearchEvent.NavigateUp -> {
                            navController.navigateUp()
                        }

                        else -> Unit
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.navMovieDetails(navController: NavController) {
    composable<MovieDetails> {
        val args = it.toRoute<MovieDetails>()
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            MovieDetailsScreen(
                args = args,
                onEvent = { event ->
                    when (event) {
                        is MovieDetailsEvent.NavigateUp -> {
                            navController.navigateUp()
                        }

                        is MovieDetailsEvent.MovieDetails -> {
                            navController.navigate(
                                MovieDetails(
                                    id = event.model.id,
                                    name = event.model.name,
                                    posterPath = event.model.posterPath,
                                    type = event.type
                                )
                            )
                        }

                        else -> Unit
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.navSettings() {
    composable<Settings> {
        SettingsScreen()
    }
}




