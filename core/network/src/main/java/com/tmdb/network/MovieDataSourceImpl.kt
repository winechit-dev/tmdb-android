package com.tmdb.network

import arrow.core.Either
import com.tmdb.data.datasource.remote.MovieDataSource
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.exception.DataException
import com.tmdb.network.handler.handleCall
import com.tmdb.network.service.MovieService
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDataSource {
    override suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.getTrendingTodayMovies(nextPage)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getPopularMovies(nextPage: Int): Either<com.tmdb.domain.exception.DataException, MoviesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(nextPage: Int): Either<com.tmdb.domain.exception.DataException, MoviesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(nextPage: Int): Either<com.tmdb.domain.exception.DataException, MoviesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlayingMovies(nextPage: Int): Either<com.tmdb.domain.exception.DataException, MoviesResponse> {
        TODO("Not yet implemented")
    }

}