package com.tmdb.domain.model

data class MovieDetailsModel(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<GenreModel>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int,
    val cast: List<CastModel> = emptyList(),
    val recommendations: List<MovieModel> = emptyList()
)
