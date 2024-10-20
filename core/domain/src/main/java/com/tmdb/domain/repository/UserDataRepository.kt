package com.tmdb.domain.repository

import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>
    suspend fun setThemeBrand(themeBrand: ThemeBrand)
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}