plugins {
    alias(libs.plugins.convention.application)
    alias(libs.plugins.convention.compose.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tmdb.mobile"
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":feature:discover"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:search"))
}

