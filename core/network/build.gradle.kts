import org.gradle.internal.impldep.com.google.api.client.googleapis.testing.auth.oauth2.MockGoogleCredential.ACCESS_TOKEN

plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.tmdb.network"

    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"${project.extra["BASE_URL"] as String}\"")
        buildConfigField("String", "API_KEY", "\"${project.extra["API_KEY"] as String}\"")
        buildConfigField("String", "ACCESS_TOKEN", "\"${project.extra["ACCESS_TOKEN"] as String}\"")
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.arrow)
}