package com.tmdb.data.repository

import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.model.SettingsData
import com.tmdb.domain.repository.SettingRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingRepositoryImpl @Inject constructor() : SettingRepository {
    override val settingsData: Flow<SettingsData>
        get() = TODO("Not yet implemented")

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        TODO("Not yet implemented")
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        TODO("Not yet implemented")
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        TODO("Not yet implemented")
    }
}