plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.compose.library)
}

android {
    namespace = "com.moviequest.discover"
}
dependencies {
    implementation(project(":core:designsystem"))
}