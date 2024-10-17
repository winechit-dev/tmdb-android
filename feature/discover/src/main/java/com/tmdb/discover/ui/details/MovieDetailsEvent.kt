package com.tmdb.discover.ui.details

import com.tmdb.domain.model.MovieDetailsModel

sealed interface MovieDetailsEvent {
    data object NavigateUp : MovieDetailsEvent
    data class OnToggleFavorite(val model: MovieDetailsModel) : MovieDetailsEvent
}