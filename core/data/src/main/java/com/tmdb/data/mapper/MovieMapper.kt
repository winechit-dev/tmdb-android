package com.tmdb.data.mapper

import com.tmdb.data.BuildConfig
import com.tmdb.data.model.GenresResponse
import com.tmdb.data.model.MovieDetailsResponse
import com.tmdb.data.model.MovieResponse
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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

fun MovieDetailsResponse.toMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        budget = budget ?: 0,
        genres = genres?.map {
            GenreModel(
                id = it.id,
                name = it.name
            )
        }.orEmpty(),
        homepage = homepage.orEmpty(),
        id = id!!,
        imdbId = imdbId.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: 0.0,
        posterPath = posterPath.createImageUrl(),
        releaseDate = releaseDate.changeFormat("yyyy-MM-dd","dd MMMM yyyy"),
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = (voteAverage ?: 0.0).toFloat(),
        voteCount = voteCount ?: 0
    )
}

fun String?.createImageUrl(): String {
    return "${BuildConfig.IMAGE_BASE_UR}/$this"
}

fun String?.changeFormat(
    originPattern: String,
    targetPattern: String,
    isUTC: Boolean = false,
    default: String = "-"
): String {
    if (this.isNullOrBlank()) return default
    return try {
        val fromP = DateTimeFormatter.ofPattern(originPattern)
        val toP = DateTimeFormatter.ofPattern(targetPattern)
        val fromLocalDate = LocalDateTime.parse(this, fromP)
        if (isUTC) {
            val fromZoneDateTime = fromLocalDate.atZone(ZoneOffset.UTC)
            return fromZoneDateTime.format(toP)
        }
        return fromLocalDate.format(toP)
    } catch (e: Exception) {
        this
    }
}