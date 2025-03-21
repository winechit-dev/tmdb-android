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
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    coreLibraryDesugaring(libs.desugaring)
    api(libs.retrofit.core)
    api(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation (libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.junit)
    testImplementation (libs.turbine)
}