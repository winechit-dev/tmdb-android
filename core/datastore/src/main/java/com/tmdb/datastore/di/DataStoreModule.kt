package com.tmdb.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.tmdb.datastore.model.SettingsDataResponse
import com.tmdb.datastore.serializer.SettingDataSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        fun providesLanguageSettingDataStore(
            @ApplicationContext context: Context,
            settingDataSerializer: SettingDataSerializer,
        ): DataStore<SettingsDataResponse> =
            DataStoreFactory.create(
                serializer = settingDataSerializer,
                migrations = listOf(),
            ) {
                context.dataStoreFile("settings.pb")
            }

    }
}