package com.tmdb.data

import app.cash.turbine.test
import com.tmdb.data.mapper.toSettingsDataModel
import com.tmdb.data.repository.SettingRepositoryImpl
import com.tmdb.datastore.PreferencesDataSource
import com.tmdb.datastore.model.DarkThemeConfigResponse
import com.tmdb.datastore.model.SettingsDataResponse
import com.tmdb.datastore.model.ThemeBrandResponse
import com.tmdb.domain.model.DarkThemeConfig
import com.tmdb.domain.model.ThemeBrand
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SettingRepositoryTest {

    private lateinit var preferencesDataSource: PreferencesDataSource
    private lateinit var settingRepository: SettingRepositoryImpl

    @Before
    fun setUp() {
        preferencesDataSource = mockk(relaxed = true)
        settingRepository = SettingRepositoryImpl(preferencesDataSource)
    }

    @Test
    fun `settingsFlow emits correct SettingsDataModel`() = runTest {
        val settingsResponse = SettingsDataResponse(
            themeBrand = ThemeBrandResponse.DEFAULT,
            darkThemeConfig = DarkThemeConfigResponse.LIGHT,
            useDynamicColor = true
        )
        val expectedSettingsDataModel = settingsResponse.toSettingsDataModel()

        coEvery { preferencesDataSource.settingsDataFlow } returns flowOf(settingsResponse)

        settingRepository.settingsFlow.test {
            assertEquals(expectedSettingsDataModel, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `setThemeBrand calls preferencesDataSource with correct value`() = runTest {
        settingRepository.setThemeBrand(ThemeBrand.ANDROID)
        coVerify { preferencesDataSource.setThemeBrand(ThemeBrandResponse.ANDROID) }
    }

    @Test
    fun `setDarkThemeConfig calls preferencesDataSource with correct value`() = runTest {
        settingRepository.setDarkThemeConfig(DarkThemeConfig.DARK)
        coVerify { preferencesDataSource.setDarkThemeConfig(DarkThemeConfigResponse.DARK) }
    }

    @Test
    fun `setDynamicColorPreference calls preferencesDataSource with correct value`() = runTest {
        settingRepository.setDynamicColorPreference(true)
        coVerify { preferencesDataSource.setDynamicColorPreference(true) }
    }
}