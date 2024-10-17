package com.tmdb.discover.ui

sealed interface DiscoverEvent {
    data class SelectedGenre(val genreId: Int) : DiscoverEvent
}