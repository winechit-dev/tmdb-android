package com.tmdb.discover.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.discover.model.GenreUIModel
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.repository.MovieRepository
import com.tmdb.ui.MovieUIModel
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
    }

    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getMovieGenres()
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
                .onRight { trendingTodayMovies ->

                    _uiState.update { state ->
                        state.copy(
                            trendingTodayMovies = trendingTodayMovies
                                .searches
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
                genres = state.genres.map { it.copy(selected = it.id == genreId) })
        }
        fetchAll()
    }
}

data class DiscoverUIState(
    val genres: List<GenreUIModel> = emptyList(),
    val selectedGenreId: Int = 100,
    val trendingTodayMovies: List<MovieUIModel> = emptyList()
)