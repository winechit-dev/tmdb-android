package com.tmdb.data.mapper

import com.tmdb.data.BuildConfig
import com.tmdb.data.model.CastResponse
import com.tmdb.data.model.GenresResponse
import com.tmdb.data.model.MovieDetailsResponse
import com.tmdb.data.model.MovieResponse
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.model.CastModel
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel
import java.time.LocalDate
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

fun MovieResponse.toMovieModel(): MovieModel {
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

fun MovieDetailsResponse.toMovieDetailsModel(
    cast: List<CastResponse>,
    recommendations: List<MovieResponse>
): MovieDetailsModel {
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
        releaseDate = releaseDate.changeFormat("yyyy-MM-dd", "dd MMM yyyy"),
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = (voteAverage ?: 0.0).toFloat(),
        voteCount = voteCount ?: 0,
        cast = cast.toCast(),
        recommendations = recommendations.map { it.toMovieModel() }
    )
}

fun List<CastResponse>.toCast(): List<CastModel> {
    return map {
        CastModel(
            castId = it.castId,
            id = it.id,
            profilePath = it.profilePath.createImageUrl(),
            originalName = it.originalName
        )
    }
}

fun String?.createImageUrl(): String {
    return "${BuildConfig.IMAGE_BASE_UR}/$this"
}

fun String?.changeFormat(
    originPattern: String,
    targetPattern: String,
    default: String = "-"
): String {
    if (this.isNullOrBlank()) return default
    return try {
        val fromP = DateTimeFormatter.ofPattern(originPattern)
        val toP = DateTimeFormatter.ofPattern(targetPattern)
        val fromLocalDate = LocalDate.parse(this, fromP)
        return fromLocalDate.format(toP)
    } catch (e: Exception) {
        this
    }
}