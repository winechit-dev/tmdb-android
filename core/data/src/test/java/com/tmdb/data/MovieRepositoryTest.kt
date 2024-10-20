package com.tmdb.data

import arrow.core.Either
import com.tmdb.data.datasource.remote.MovieDataSource
import com.tmdb.data.repository.MovieRepositoryImpl
import com.tmdb.database.FavoriteDao
import com.tmdb.domain.model.FavoriteMovieModel
import com.tmdb.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MovieRepositoryTest {

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var repository: MovieRepository
    private lateinit var favoriteDao: FavoriteDao

    @Before
    fun setUp() {
        favoriteDao = mockk(relaxed = true)
        movieDataSource = mockk(relaxed = true)
        repository = MovieRepositoryImpl(
            movieDataSource,
            favoriteDao
        )
    }

    @Test
    fun `toggleFavorite should delete favorite when model is favorite`() = runBlocking {

        val favoriteMovieModel =
            FavoriteMovieModel(movieId = 123, favorite = true, posterPath = "", name = "")

        val result = repository.toggleFavorite(favoriteMovieModel)

        coVerify { favoriteDao.deleteAFavorite(movieId = 123) }
        assertEquals(Either.Right(Unit), result)
    }

    @Test
    fun `toggleFavorite should insert favorite when model is not favorite`() = runBlocking {
        val favoriteMovieModel =
            FavoriteMovieModel(movieId = 123, favorite = false, posterPath = "", name = "")

        val result = repository.toggleFavorite(favoriteMovieModel)


        coVerify { favoriteDao.insertFavorite(any()) } // Verifying that insertFavorite was called
        assertEquals(Either.Right(Unit), result)
    }

    @Test
    fun `toggleFavorite should return IOOperation exception on IOException`() = runBlocking {
        // Arrange
        val favoriteMovieModel = FavoriteMovieModel(movieId = 123, favorite = true, posterPath = "", name = "")
        val ioExceptionMessage = "Disk read error"
        coEvery { favoriteDao.deleteAFavorite(movieId = any()) } throws IOException(
            ioExceptionMessage
        )

        // Act
        val result = repository.toggleFavorite(favoriteMovieModel)

        // Assert
        assert(result is Either.Left)
        assertEquals(ioExceptionMessage, (result as Either.Left).value.message)
    }
}
