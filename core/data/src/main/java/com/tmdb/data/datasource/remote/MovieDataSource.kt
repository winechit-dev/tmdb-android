package com.tmdb.data.datasource.remote

import arrow.core.Either
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.exception.DataException

interface MovieDataSource {
    suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getPopularMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getUpcomingMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getTopRatedMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getNowPlayingMovies(nextPage: Int): Either<DataException, MoviesResponse>
}