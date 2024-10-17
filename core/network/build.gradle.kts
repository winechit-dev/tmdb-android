
plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.tmdb.network"

    defaultConfig {
        buildConfigField ("String", "BASE_URL", "\"${project.extra["BASE_URL"] as String}\"")
        buildConfigField ("String", "API_KEY", "\"${project.extra["API_KEY"] as String}\"")
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.arrow)
}