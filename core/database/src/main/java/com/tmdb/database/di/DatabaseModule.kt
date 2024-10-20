package com.tmdb.database.di

import android.content.Context
import com.tmdb.database.AppDatabase
import com.tmdb.database.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): AppDatabase {
            return AppDatabase.buildDatabase(context)
        }

        @Provides
        @Singleton
        fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
            return appDatabase.favoriteDao()
        }
    }
}