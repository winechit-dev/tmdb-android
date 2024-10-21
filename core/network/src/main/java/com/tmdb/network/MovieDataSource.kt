package com.tmdb.network

import arrow.core.Either
import com.tmdb.common.exception.DataException
import com.tmdb.network.model.CastResponse
import com.tmdb.network.model.GenresResponse
import com.tmdb.network.model.MovieDetailsResponse
import com.tmdb.network.model.MovieResponse
import com.tmdb.network.model.MoviesResponse

interface MovieDataSource {
    suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getPopularMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getUpcomingMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getTopRatedMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getNowPlayingMovies(nextPage: Int): Either<DataException, MoviesResponse>
    suspend fun getMovieGenres(): Either<DataException, GenresResponse>
    suspend fun getMovieDetails(movieId: Int): Either<DataException, MovieDetailsResponse>
    suspend fun getCast(movieId: Int): Either<DataException, List<CastResponse>>
    suspend fun getRecommendations(movieId: Int): Either<DataException, List<MovieResponse>>
    suspend fun searchMovie(query: String): Either<DataException, MoviesResponse>
}