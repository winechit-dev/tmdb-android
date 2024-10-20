package com.tmdb.domain.repository

import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.model.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val settingsData: Flow<SettingsData>
    suspend fun setThemeBrand(themeBrand: ThemeBrand)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}