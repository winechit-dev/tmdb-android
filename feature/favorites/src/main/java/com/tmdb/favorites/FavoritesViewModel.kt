package com.tmdb.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.designsystem.utils.UserMessageManager
import com.tmdb.domain.model.FavoriteMovieModel
import com.tmdb.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteMovieModel>> = repository
        .getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000L)
        )

    fun onToggleFavorite(model: FavoriteMovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .toggleFavorite(model)
                .onLeft { error ->
                    UserMessageManager.showMessage(error.message.toString())
                }
        }
    }
}
