package com.tmdb.discover.ui.details

import com.tmdb.ui.model.MovieUIModel

sealed interface MovieDetailsEvent {
    data object NavigateUp : MovieDetailsEvent
    data object OnToggleFavorite : MovieDetailsEvent
    data class MovieDetails(val model: MovieUIModel, val type:String) : MovieDetailsEvent
}