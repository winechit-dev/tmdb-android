package com.tmdb.domain.model

data class SettingsDataModel(
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
)

enum class DarkThemeConfig {
    FOLLOW_SYSTEM,
    LIGHT,
    DARK,
}

enum class ThemeBrand {
    DEFAULT,
    ANDROID,
}