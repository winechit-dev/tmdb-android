package com.tmdb.search.mapper

import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.search.model.SearchMovieUIModel

fun List<MovieModel>.toSearchMoviesUIModel(genres: List<GenreModel>): List<SearchMovieUIModel> {
    return this.map { it.toSearchMovieUIModel(genres) }
}

private fun MovieModel.toSearchMovieUIModel(genres: List<GenreModel>): SearchMovieUIModel {
    return SearchMovieUIModel(
        id = id,
        posterPath = posterPath,
        name = originalTitle,
        genres = genres.filter { this.genreIds.contains(it.id) }.map { it.name },
        releaseDate = releaseDate,
        overview = overview
    )
}