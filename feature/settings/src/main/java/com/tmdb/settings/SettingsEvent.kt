package com.tmdb.settings

import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand

sealed interface SettingsEvent {
    data class ChangeThemeBrand(val themeBrand: ThemeBrand) : SettingsEvent
    data class ChangeDarkThemeConfig(val darkThemeConfig: DarkThemeConfig) : SettingsEvent
    data class ChangeDynamicColorPreference(val useDynamicColor: Boolean) : SettingsEvent
}