plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.moviequest.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
}