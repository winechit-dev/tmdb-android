package com.tmdb.search.model

data class SearchMovieUIModel(
    val id: Int,
    val name: String,
    val posterPath: String,
    val genres: List<String>,
    val overview: String,
    val releaseDate: String
)