package com.tmdb.domain.model

data class GenresModel(
    val genres: List<GenreModel>
)

data class GenreModel(
    val id: Int,
    val name: String
)