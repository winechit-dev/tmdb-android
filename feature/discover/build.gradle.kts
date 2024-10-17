plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.tmdb.discover"
}
dependencies {
    implementation(project(":core:designsystem"))
}