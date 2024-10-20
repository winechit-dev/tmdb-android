package com.tmdb.data.repository

import com.tmdb.data.mapper.toSettingsDataModel
import com.tmdb.datastore.PreferencesDataSource
import com.tmdb.datastore.model.DarkThemeConfigResponse
import com.tmdb.datastore.model.ThemeBrandResponse
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.SettingsDataModel
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.repository.SettingRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : SettingRepository {
    override val settingsDataModel: Flow<SettingsDataModel>
        get() = preferencesDataSource
            .settingsDataFlow
            .map { it.toSettingsDataModel() }

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        preferencesDataSource.setThemeBrand(
            when (themeBrand) {
                ThemeBrand.DEFAULT -> ThemeBrandResponse.DEFAULT
                ThemeBrand.ANDROID -> ThemeBrandResponse.ANDROID
            }
        )
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        preferencesDataSource.setDarkThemeConfig(
            when (darkThemeConfig) {
                DarkThemeConfig.FOLLOW_SYSTEM -> DarkThemeConfigResponse.FOLLOW_SYSTEM
                DarkThemeConfig.LIGHT -> DarkThemeConfigResponse.LIGHT
                DarkThemeConfig.DARK -> DarkThemeConfigResponse.DARK
            }
        )
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        preferencesDataSource.setDynamicColorPreference(useDynamicColor)
    }
}