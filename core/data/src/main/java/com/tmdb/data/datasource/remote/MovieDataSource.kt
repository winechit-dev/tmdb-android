package com.tmdb.data.datasource.remote

import arrow.core.Either
import com.tmdb.data.model.CastResponse
import com.tmdb.data.model.CreditsResponse
import com.tmdb.data.model.GenresResponse
import com.tmdb.data.model.MovieDetailsResponse
import com.tmdb.data.model.MovieResponse
import com.tmdb.data.model.MoviesResponse
import com.tmdb.domain.exception.DataException

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