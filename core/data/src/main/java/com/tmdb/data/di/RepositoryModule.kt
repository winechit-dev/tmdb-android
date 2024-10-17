package com.tmdb.data.di

import com.tmdb.data.repository.MovieRepositoryImpl
import com.tmdb.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMasterDataRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
