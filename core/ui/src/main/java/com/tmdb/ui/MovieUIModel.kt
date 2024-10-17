package com.tmdb.ui

data class MovieUIModel (
    val id: Int,
    val posterPath : String,
    val genreIds: List<Int>,
)