package com.tmdb.discover.ui

import com.tmdb.ui.MovieUIModel

sealed interface DiscoverEvent {
    data class SelectedGenre(val genreId: Int) : DiscoverEvent
    data class MovieDetails(val model: MovieUIModel,val type:String) : DiscoverEvent
    data object Search : DiscoverEvent
}