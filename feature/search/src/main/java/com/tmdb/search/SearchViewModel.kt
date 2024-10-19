package com.tmdb.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.designsystem.utils.UserMessageManager
import com.tmdb.domain.model.GenreModel
import com.tmdb.domain.repository.MovieRepository
import com.tmdb.search.mapper.toSearchMoviesUIModel
import com.tmdb.search.model.SearchMovieUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState: StateFlow<SearchUIState> = _uiState.asStateFlow()

    init {
        getGenres()
        uiState
            .map { it.query }
            .distinctUntilChanged()
            .debounce(1000L)
            .onEach { query -> if (query.isNotBlank()) searchMovie(query) }
            .launchIn(viewModelScope)
    }


    fun queryChanged(query: String) {
        _uiState.update {
            if (query.isBlank()) {
                it.copy(query = query, movies = null, userMessage = "")
            } else {
                it.copy(query = query, userMessage = "")
            }
        }
    }

    fun updateFocusRequest() {
        _uiState.update {
            it.copy(focusRequested = true)
        }
    }

    private fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(loading = true) }
            repository
                .searchMovie(query)
                .onLeft { UserMessageManager.showMessage(it.message.toString()) }
                .onRight { results ->
                    _uiState.update {
                        val movies = results.toSearchMoviesUIModel(it.genres)
                        it.copy(
                            movies = movies,
                            loading = false,
                            userMessage = if (movies.isEmpty()) "No Result!" else ""
                        )
                    }
                }.onLeft {

                }
        }
    }

    private fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getMovieGenres()
                .onRight { result ->
                    _uiState.update { state ->
                        state.copy(genres = result.genres)
                    }
                }
        }
    }
}

data class SearchUIState(
    val query: String = "",
    val loading: Boolean = false,
    val userMessage: String = "",
    val movies: List<SearchMovieUIModel>? = null,
    val genres: List<GenreModel> = emptyList(),
    val focusRequested: Boolean = false
)