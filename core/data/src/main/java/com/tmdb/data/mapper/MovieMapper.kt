package com.tmdb.data.mapper

import com.tmdb.data.BuildConfig
import com.tmdb.data.model.GenresResponse
import com.tmdb.data.model.MovieResponse
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel

fun MoviesResponse.toMoviesModel(): MoviesModel {
    return MoviesModel(
        page = page,
        searches = searches.map { it.toMovieModel() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

private fun MovieResponse.toMovieModel(): MovieModel {
    return MovieModel(
        adult = adult,
        backdropPath = backdropPath.orEmpty(),
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath.createImageUrl(),
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenresResponse.toGenresModel(): GenresModel {
    return GenresModel(
        genres = this.genres.map {
            GenreModel(
                id = it.id,
                name = it.name
            )
        }
    )
}

fun String.createImageUrl(): String {
    return "${BuildConfig.IMAGE_BASE_UR}/$this"
}