plugins {
    alias(libs.plugins.convention.library)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tmdb.data"

    defaultConfig {
        buildConfigField(
            "String",
            "IMAGE_BASE_UR",
            "\"${project.extra["IMAGE_BASE_UR"] as String}\""
        )
    }
}

dependencies {
    implementation(project(":core:domain"))
    api(libs.retrofit.core)
    api(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
}