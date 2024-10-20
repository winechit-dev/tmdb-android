package com.tmdb.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.SettingsDataModel
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {

    val settings = settingRepository
        .settingsFlow
        .stateIn(
            scope = viewModelScope,
            initialValue = SettingsDataModel(
                themeBrand = ThemeBrand.DEFAULT,
                darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                useDynamicColor = true
            ),
            started = SharingStarted.WhileSubscribed(5000L)
        )

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeThemeBrand -> setThemeBrand(event.themeBrand)
            is SettingsEvent.ChangeDarkThemeConfig -> setDarkThemeConfig(event.darkThemeConfig)
            is SettingsEvent.ChangeDynamicColorPreference -> setDynamicColorPreference(event.useDynamicColor)
        }
    }

    private fun setThemeBrand(themeBrand: ThemeBrand) {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepository.setThemeBrand(themeBrand)
        }
    }

    private fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }

    private fun setDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepository.setDynamicColorPreference(useDynamicColor)
        }
    }
}