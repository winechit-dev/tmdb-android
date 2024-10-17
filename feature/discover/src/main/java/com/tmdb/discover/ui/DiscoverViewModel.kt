package com.tmdb.discover.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.discover.genresPreview
import com.tmdb.discover.model.GenreUIModel
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
        getTrendingTodayMovies()
    }

    private fun getTrendingTodayMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getTrendingTodayMovies(1)
                .onRight { trendingTodayMovies ->
                    _uiState.update {
                        it.copy(trendingTodayMovies = trendingTodayMovies.searches.toMoviesUIModel())
                    }
                }.onLeft {

                }
        }
    }
}

data class DiscoverUIState(
    val genres: List<GenreUIModel> = genresPreview,
    val selectedGenre: String = "",
    val trendingTodayMovies: List<MovieUIModel> = emptyList()
)