plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.tmdb.settings"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
}