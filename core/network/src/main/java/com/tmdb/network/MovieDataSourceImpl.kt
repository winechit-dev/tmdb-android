package com.tmdb.network

import arrow.core.Either
import com.tmdb.common.exception.DataException
import com.tmdb.network.model.CastResponse
import com.tmdb.network.model.GenresResponse
import com.tmdb.network.model.MovieDetailsResponse
import com.tmdb.network.model.MovieResponse
import com.tmdb.network.model.MoviesResponse
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

    override suspend fun getPopularMovies(nextPage: Int): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.getPopularMovies(nextPage)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getUpcomingMovies(nextPage: Int): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.getUpcomingMovies(nextPage)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getTopRatedMovies(nextPage: Int): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.getTopRatedMovies(nextPage)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getNowPlayingMovies(nextPage: Int): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.getNowPlayingMovies(nextPage)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getMovieGenres(): Either<DataException, GenresResponse> {
        return handleCall(
            apiCall = {
                service.getMovieGenres()
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getMovieDetails(movieId: Int): Either<DataException, MovieDetailsResponse> {
        return handleCall(
            apiCall = {
                service.getMovieDetails(movieId)
            },
            mapper = { data ->
                data
            }
        )
    }

    override suspend fun getCast(movieId: Int): Either<DataException, List<CastResponse>> {
        return handleCall(
            apiCall = {
                service.getCreditDetails(movieId)
            },
            mapper = { data ->
                data.cast
            }
        )
    }

    override suspend fun getRecommendations(movieId: Int): Either<DataException, List<MovieResponse>> {
        return handleCall(
            apiCall = {
                service.getRecommendations(movieId = movieId, page = 1)
            },
            mapper = { data ->
                data.results
            }
        )
    }

    override suspend fun searchMovie(query: String): Either<DataException, MoviesResponse> {
        return handleCall(
            apiCall = {
                service.searchMovie(query = query, page = 1)
            },
            mapper = { data ->
                data
            }
        )
    }
}