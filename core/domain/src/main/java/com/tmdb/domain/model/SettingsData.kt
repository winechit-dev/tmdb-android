package com.tmdb.domain.model

data class SettingsData(
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