package com.tmdb.data.mapper

import com.tmdb.data.BuildConfig
import com.tmdb.network.model.CastResponse
import com.tmdb.network.model.GenresResponse
import com.tmdb.network.model.MovieDetailsResponse
import com.tmdb.network.model.MovieResponse
import com.tmdb.network.model.MoviesResponse
import com.tmdb.database.FavoriteEntity
import com.tmdb.domain.model.CastModel
import com.tmdb.domain.model.FavoriteMovieModel
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun com.tmdb.network.model.MoviesResponse.toMoviesModel(): MoviesModel {
    return MoviesModel(
        page = page,
        results = results.map { it.toMovieModel() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun com.tmdb.network.model.MovieResponse.toMovieModel(): MovieModel {
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
        releaseDate = releaseDate.changeFormat("yyyy-MM-dd", "dd MMM yyyy"),
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun com.tmdb.network.model.GenresResponse.toGenresModel(): GenresModel {
    return GenresModel(
        genres = this.genres.map {
            GenreModel(
                id = it.id,
                name = it.name
            )
        }
    )
}

fun com.tmdb.network.model.MovieDetailsResponse.toMovieDetailsModel(
    cast: List<com.tmdb.network.model.CastResponse>,
    recommendations: List<com.tmdb.network.model.MovieResponse>
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

fun List<com.tmdb.network.model.CastResponse>.toCast(): List<CastModel> {
    return map {
        CastModel(
            castId = it.castId,
            id = it.id,
            profilePath = it.profilePath.createImageUrl(),
            originalName = it.originalName
        )
    }
}

fun FavoriteEntity.toFavoriteModel(): FavoriteMovieModel {
    return FavoriteMovieModel(
        movieId = movieId,
        name = name,
        favorite = favorite,
        posterPath = posterPath
    )
}

val currentDateTime: LocalDateTime get() = LocalDateTime.now()

val LocalDateTime.millis: Long
    get() = toInstant(ZonedDateTime.now().offset).toEpochMilli()

fun FavoriteMovieModel.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        movieId = movieId,
        name = name,
        favorite = true,
        posterPath = posterPath,
        createdAt = currentDateTime.millis
    )
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