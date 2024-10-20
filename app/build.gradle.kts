plugins {
    alias(libs.plugins.convention.application)
    alias(libs.plugins.convention.compose.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.screenshot)
}

android {
    namespace = "com.tmdb.mobile"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    coreLibraryDesugaring(libs.desugaring)
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":feature:discover"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))

    screenshotTestImplementation(libs.androidx.ui.tooling)
}

