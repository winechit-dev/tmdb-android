package com.moviequest.discover.ui

import androidx.lifecycle.ViewModel
import com.moviequest.discover.genresPreview
import com.moviequest.discover.model.GenreUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DiscoverViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoverUIState())
    val uiState: StateFlow<DiscoverUIState> = _uiState.asStateFlow()
}

data class DiscoverUIState(
    val genres: List<GenreUIModel> = genresPreview,
    val selectedGenre: String = ""
)