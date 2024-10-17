package com.tmdb.data.repository

import arrow.core.Either
import com.tmdb.data.datasource.remote.MovieDataSource
import com.tmdb.data.mapper.toGenresModel
import com.tmdb.data.mapper.toMoviesModel
import com.tmdb.domain.exception.DataException
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MoviesModel
import com.tmdb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getTrendingTodayMovies(nextPage: Int): Either<DataException, MoviesModel> {
        return movieDataSource
            .getTrendingTodayMovies(nextPage)
            .map { it.toMoviesModel() }
    }

    override suspend fun getPopularMovies(nextPage: Int): Either<DataException, MoviesModel> {
        return movieDataSource
            .getPopularMovies(nextPage)
            .map { it.toMoviesModel() }
    }

    override suspend fun getUpcomingMovies(nextPage: Int): Either<DataException, MoviesModel> {
        return movieDataSource
            .getUpcomingMovies(nextPage)
            .map { it.toMoviesModel() }
    }

    override suspend fun getTopRatedMovies(nextPage: Int): Either<DataException, MoviesModel> {
        return movieDataSource
            .getTopRatedMovies(nextPage)
            .map { it.toMoviesModel() }
    }

    override suspend fun getNowPlayingMovies(nextPage: Int): Either<DataException, MoviesModel> {
        return movieDataSource
            .getNowPlayingMovies(nextPage)
            .map { it.toMoviesModel() }
    }

    override suspend fun getMovieGenres(): Either<DataException, GenresModel> {
        return movieDataSource
            .getMovieGenres()
            .map { it.toGenresModel() }
    }
}