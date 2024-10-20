plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.tmdb.datastore"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.dataStore.pref)
}