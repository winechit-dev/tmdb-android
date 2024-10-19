package com.tmdb.domain.repository

import arrow.core.Either
import com.tmdb.domain.exception.DataException
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel

interface MovieRepository {
    suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getPopularMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getUpcomingMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getTopRatedMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getNowPlayingMovies(nextPage: Int): Either<DataException, MoviesModel>
    suspend fun getMovieGenres(): Either<DataException, GenresModel>
    suspend fun getMovieDetails(movieId: Int): Either<DataException, MovieDetailsModel>
    suspend fun searchMovie(query: String): Either<DataException, List<MovieModel>>
}