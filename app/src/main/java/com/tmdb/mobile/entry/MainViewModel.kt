package com.tmdb.mobile.entry

import androidx.lifecycle.viewModelScope
import com.tmdb.designsystem.utils.UserMessageManager
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.SettingsDataModel
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.repository.SettingRepository
import com.tmdb.mobile.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: SettingRepository
) : BaseViewModel<MainEvent>() {
    private val userMessageManager by lazy { UserMessageManager }

    val uiState = repository.settingsFlow
        .map { MainUIState(settings = it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = MainUIState(),
            started = SharingStarted.WhileSubscribed(5000L)
        )

    fun userMessageShown() {
        userMessageManager.userMessageShown()
    }

    init {
        handelUserMessage()
    }

    private fun handelUserMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            userMessageManager
                .messages
                .collectLatest { message ->
                    if (message != null) {
                        emitEvent(MainEvent.UserMessage(message))
                    } else {
                        emitEvent(MainEvent.Idle)
                    }
                }
        }
    }
}

data class MainUIState(
    val settings: SettingsDataModel = SettingsDataModel(
        themeBrand = ThemeBrand.DEFAULT,
        darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
        useDynamicColor = true
    )
)