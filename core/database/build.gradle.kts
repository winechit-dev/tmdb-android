plugins {
    alias(libs.plugins.convention.library)
}

android {
    namespace = "com.tmdb.database"
    defaultConfig {
        buildConfigField(
            "String",
            "PASS_PHRASE",
            "\"${project.extra["PASS_PHRASE"] as String}\""
        )
    }
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.sqlcipher)
}