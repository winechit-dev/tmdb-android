package com.tmdb.data.mapper

import com.tmdb.datastore.model.DarkThemeConfigResponse
import com.tmdb.datastore.model.SettingsDataResponse
import com.tmdb.datastore.model.ThemeBrandResponse
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.SettingsDataModel
import com.tmdb.domain.model.ThemeBrand

fun SettingsDataResponse.toSettingsDataModel(): SettingsDataModel {
    return SettingsDataModel(
        themeBrand = when (themeBrand) {
            ThemeBrandResponse.DEFAULT -> ThemeBrand.DEFAULT
            ThemeBrandResponse.ANDROID -> ThemeBrand.ANDROID
        },
        darkThemeConfig = when (darkThemeConfig) {
            DarkThemeConfigResponse.FOLLOW_SYSTEM -> DarkThemeConfig.FOLLOW_SYSTEM
            DarkThemeConfigResponse.LIGHT -> DarkThemeConfig.LIGHT
            DarkThemeConfigResponse.DARK -> DarkThemeConfig.DARK

        },
        useDynamicColor = useDynamicColor
    )
}