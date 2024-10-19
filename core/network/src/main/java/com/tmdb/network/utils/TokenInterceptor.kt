package com.tmdb.network.utils

import com.tmdb.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

fun String.prefixBearer() = "Bearer $this"

fun tokenInterceptor(
    chain: Interceptor.Chain
): Response {
    val accessToken = BuildConfig.ACCESS_TOKEN.prefixBearer()
    val originalRequest = chain.request()
    val request = originalRequest.newBuilder()
        .addHeader("Content-Type", "application/json-patch+json")
        .addHeader("Authorization", accessToken.prefixBearer())
        .addHeader("accept", "text/plain")
        .build()
    return chain.proceed(request = request)
}

