plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.app.analytics"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}