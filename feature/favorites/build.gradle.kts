plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.tmdb.favorites"
}
dependencies {
    implementation(project(":core:designsystem"))
}