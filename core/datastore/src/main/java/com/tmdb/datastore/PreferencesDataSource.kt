package com.tmdb.datastore

import androidx.datastore.core.DataStore
import com.tmdb.datastore.model.DarkThemeConfigResponse
import com.tmdb.datastore.model.SettingsDataResponse
import com.tmdb.datastore.model.ThemeBrandResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PreferencesDataSource @Inject constructor(
    private val settingsDataStore: DataStore<SettingsDataResponse>,
) {
    val settingsDataFlow: Flow<SettingsDataResponse>
        get() = settingsDataStore.data

    suspend fun setThemeBrand(themeBrand: ThemeBrandResponse) {
        settingsDataStore.updateData { it.copy(themeBrand = themeBrand) }
    }

    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        settingsDataStore.updateData {
            it.copy(useDynamicColor = useDynamicColor)
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfigResponse: DarkThemeConfigResponse) {
        settingsDataStore.updateData {
            it.copy(darkThemeConfig = darkThemeConfigResponse)
        }
    }
}