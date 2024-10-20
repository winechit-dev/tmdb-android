package com.tmdb.data.repository

import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand
import com.tmdb.domain.model.UserData
import com.tmdb.domain.repository.UserDataRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserDataRepositoryImpl @Inject constructor() : UserDataRepository {
    override val userData: Flow<UserData>
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