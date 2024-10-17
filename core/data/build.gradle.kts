plugins {
    alias(libs.plugins.convention.library)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tmdb.data"
}

dependencies {
    api(libs.retrofit.core)
    api(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
}