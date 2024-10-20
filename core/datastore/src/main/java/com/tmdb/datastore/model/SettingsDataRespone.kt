package com.tmdb.datastore.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SettingsDataResponse(
    @SerialName("theme_brand") val themeBrand: ThemeBrandResponse,
    @SerialName("dark_theme_config") val darkThemeConfig: DarkThemeConfigResponse,
    @SerialName("use_dynamic_color") val useDynamicColor: Boolean,
)

enum class DarkThemeConfigResponse {
    FOLLOW_SYSTEM,
    LIGHT,
    DARK,
}

enum class ThemeBrandResponse {
    DEFAULT,
    ANDROID,
}