package com.tmdb.search

import com.tmdb.search.model.SearchMovieUIModel

sealed interface SearchEvent {
    data object NavigateUp : SearchEvent
    data class QueryChanged(val query: String) : SearchEvent
    data class MovieDetails(val model: SearchMovieUIModel) : SearchEvent
    data object FocusRequested : SearchEvent
}