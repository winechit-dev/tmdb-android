plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.tmdb.search"
}
dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
}