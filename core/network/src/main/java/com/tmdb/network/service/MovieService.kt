package com.tmdb.network.service

import com.tmdb.data.model.CreditsResponse
import com.tmdb.data.model.GenresResponse
import com.tmdb.data.model.MovieDetailsResponse
import com.tmdb.data.model.MoviesResponse
import com.tmdb.network.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val STARTING_PAGE_INDEX = 0
const val LANGUAGE = "en"

interface MovieService {
    @GET("trending/movie/day")
    suspend fun getTrendingTodayMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en"
    ): Response<GenresResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en"
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<CreditsResponse>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Response<MoviesResponse>
}