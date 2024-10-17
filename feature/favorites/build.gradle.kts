plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.moviequest.favorites"
}
dependencies {
    implementation(project(":core:designsystem"))
}