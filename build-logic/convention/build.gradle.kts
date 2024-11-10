import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {

    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}


gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "conventionPluginApplication.android.application"
            implementationClass = "plugin.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "conventionPluginLibrary.android.library"
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }

        register("composeApplication") {
            id = "conventionPluginApplication.compose.application"
            implementationClass = "plugin.ComposeApplicationConventionPlugin"
        }

        register("composeLibrary") {
            id = "conventionPluginLibrary.compose.library"
            implementationClass = "plugin.ComposeLibraryConventionPlugin"
        }
    }
}