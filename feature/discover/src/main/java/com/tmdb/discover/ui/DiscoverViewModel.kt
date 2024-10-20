package com.tmdb.discover.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.designsystem.utils.UserMessageManager
import com.tmdb.discover.model.GenreUIModel
import com.tmdb.domain.repository.MovieRepository
import com.tmdb.ui.model.MovieUIModel
import com.tmdb.ui.toMoviesUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoverUIState())
    val uiState: StateFlow<DiscoverUIState> = _uiState.asStateFlow()

    init {
        getGenres()
        fetchAll()
    }

    private fun fetchAll() {
        getTrendingTodayMovies()
        getPopularMovies()
        getUpcomingMovies()
        getTopRatedMovies()
        getNowPlayingMovies()
    }


    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getMovieGenres()
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight {

                    _uiState.update { state ->
                        val default = listOf(
                            GenreUIModel(
                                id = 100,
                                name = "All",
                                selected = true
                            )
                        )
                        val genres = default + it.genres.map { genre ->
                            GenreUIModel(
                                id = genre.id,
                                name = genre.name,
                                selected = state.selectedGenreId == genre.id
                            )
                        }
                        state.copy(genres = genres)
                    }
                }
        }
    }

    private fun getTrendingTodayMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getTrendingTodayMovies(1)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { movies ->

                    _uiState.update { state ->
                        state.copy(
                            trendingTodayMovies = movies
                                .results
                                .toMoviesUIModel(state.selectedGenreId)
                        )
                    }
                }.onLeft {

                }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getPopularMovies(1)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { movies ->
                    _uiState.update { state ->
                        state.copy(
                            popularMovies = movies
                                .results
                                .toMoviesUIModel(state.selectedGenreId)
                        )
                    }
                }.onLeft {

                }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getUpcomingMovies(1)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { movies ->
                    _uiState.update { state ->
                        state.copy(
                            upcomingMovies = movies
                                .results
                                .toMoviesUIModel(state.selectedGenreId)
                        )
                    }
                }.onLeft {

                }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getTopRatedMovies(1)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { movies ->
                    _uiState.update { state ->
                        state.copy(
                            topRatedMovies = movies
                                .results
                                .toMoviesUIModel(state.selectedGenreId)
                        )
                    }
                }.onLeft {

                }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getNowPlayingMovies(1)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { movies ->
                    _uiState.update { state ->
                        state.copy(
                            nowPlayingMovies = movies
                                .results
                                .toMoviesUIModel(state.selectedGenreId)
                        )
                    }
                }.onLeft {

                }
        }
    }

    fun onSelectedGenre(genreId: Int) {
        _uiState.update { state ->
            state.copy(
                selectedGenreId = genreId,
                genres = state.genres.map { it.copy(selected = it.id == genreId) }
            )
        }
        fetchAll()
    }
}

data class DiscoverUIState(
    val genres: List<GenreUIModel> = emptyList(),
    val selectedGenreId: Int = 100,
    val trendingTodayMovies: List<MovieUIModel>? = null,
    val popularMovies: List<MovieUIModel>? = null,
    val upcomingMovies: List<MovieUIModel>? = null,
    val nowPlayingMovies: List<MovieUIModel>? = null,
    val topRatedMovies: List<MovieUIModel>? = null
)