package com.tmdb.discover.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.domain.model.MovieDetailsModel
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
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id = savedStateHandle.get<Int>("id")!!
    private val _uiState = MutableStateFlow(MovieDetailsUIState())
    val uiState: StateFlow<MovieDetailsUIState> = _uiState.asStateFlow()

    init {
        getDetails()
    }

    private fun getDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getMovieDetails(id)
                .onRight { details ->
                    _uiState.update { state ->
                        state.copy(
                            loading = false,
                            details = details,
                            recommendations = details.recommendations.toMoviesUIModel()
                        )
                    }
                }.onLeft {
                    _uiState.update { it.copy(loading = false) }
                }
        }
    }
}

data class MovieDetailsUIState(
    val loading: Boolean = true,
    val details: MovieDetailsModel? = null,
    val recommendations : List<MovieUIModel> = emptyList()
)