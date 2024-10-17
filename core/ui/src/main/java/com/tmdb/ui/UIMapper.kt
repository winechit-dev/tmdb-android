package com.tmdb.ui

import com.tmdb.domain.model.MovieModel

fun List<MovieModel>.toMoviesUIModel(): List<MovieUIModel> {
    return this.map { it.toMovieUIModel() }
}

private fun MovieModel.toMovieUIModel(): MovieUIModel {
    return MovieUIModel(
        id = id,
        posterPath = posterPath,
        genreIds = genreIds
    )
}