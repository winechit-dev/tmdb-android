package com.tmdb.domain.model

data class FavoriteMovieModel(
    val movieId: Int,
    val name: String,
    val favorite: Boolean,
    val posterPath: String
)
