package com.tmdb.domain.repository

import arrow.core.Either
import com.tmdb.domain.exception.DataException
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MoviesModel

interface MovieRepository {
    suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getMovieGenres(): Either<DataException, GenresModel>
}