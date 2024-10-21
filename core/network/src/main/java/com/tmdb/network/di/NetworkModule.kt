package com.tmdb.network.di

import android.content.Context
import com.tmdb.network.MovieDataSource
import com.tmdb.network.BuildConfig
import com.tmdb.network.MovieDataSourceImpl
import com.tmdb.network.utils.createOkHttpClient
import com.tmdb.network.utils.createRetrofitClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        @Singleton
        @Provides
        fun providesCoroutineScope(): CoroutineScope {
            return CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            @ApplicationContext
            context: Context
        ): OkHttpClient {
            return createOkHttpClient(
                context,
                HttpLoggingInterceptor()
            )
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, networkJson: Json): Retrofit {
            return createRetrofitClient(BuildConfig.BASE_URL, okHttpClient, networkJson)
        }
    }

    @Binds
    abstract fun bindMovieDataSource(
        movieDataSourceImpl: MovieDataSourceImpl
    ): MovieDataSource
}
