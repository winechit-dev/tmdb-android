package com.tmdb.discover.ui.details

import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.ui.MovieUIModel

sealed interface MovieDetailsEvent {
    data object NavigateUp : MovieDetailsEvent
    data class OnToggleFavorite(val model: MovieDetailsModel) : MovieDetailsEvent
    data class MovieDetails(val model: MovieUIModel, val type:String) : MovieDetailsEvent
}