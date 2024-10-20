package com.tmdb.data.repository

import arrow.core.Either
import arrow.core.flatMap
import com.tmdb.data.datasource.remote.MovieDataSource
import com.tmdb.data.mapper.toFavoriteEntity
import com.tmdb.data.mapper.toFavoriteModel
import com.tmdb.data.mapper.toGenresModel
import com.tmdb.data.mapper.toMovieDetailsModel
import com.tmdb.data.mapper.toMoviesModel
import com.tmdb.database.FavoriteDao
import com.tmdb.domain.exception.DataException
import com.tmdb.domain.model.FavoriteMovieModel
import com.tmdb.domain.model.GenresModel
import com.tmdb.domain.model.MovieDetailsModel
import com.tmdb.domain.model.MovieModel
import com.tmdb.domain.model.MoviesModel
import com.tmdb.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val favoriteDao: FavoriteDao
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

    override suspend fun getMovieDetails(movieId: Int): Either<DataException, MovieDetailsModel> {
        return movieDataSource
            .getMovieDetails(movieId)
            .flatMap { details ->
                movieDataSource
                    .getCast(movieId)
                    .flatMap { cast ->
                        movieDataSource
                            .getRecommendations(movieId)
                            .map { recommendations ->
                                details.toMovieDetailsModel(cast, recommendations)
                            }
                    }
            }
    }

    override suspend fun searchMovie(query: String): Either<DataException, List<MovieModel>> {
        return movieDataSource
            .searchMovie(query)
            .map { it.toMoviesModel().results }
    }

    override fun getAllFavorites(): Flow<List<FavoriteMovieModel>> {
        return favoriteDao
            .getAllFavorites()
            .map { favorites ->
                favorites.map { it.toFavoriteModel() }
            }
    }

    override fun isFavorite(movieId: Int): Flow<Boolean> {
        return favoriteDao.isFavorite(movieId)
    }

    override suspend fun toggleFavorite(model: FavoriteMovieModel): Either<DataException, Unit> {
        return try {
            if (model.favorite) {
                favoriteDao.deleteAFavorite(movieId = model.movieId)
            } else {
                favoriteDao.insertFavorite(model.toFavoriteEntity())
            }
            Either.Right(Unit)
        } catch (e: IOException) {
            Either.Left(DataException.IOOperation(e.message ?: e.localizedMessage))
        }
    }

    override suspend fun deleteAllFavorites() {
        favoriteDao.deleteAllFavorites()
    }
}