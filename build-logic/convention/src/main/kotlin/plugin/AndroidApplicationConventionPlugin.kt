package plugin

import com.android.build.api.dsl.ApplicationExtension
import config.Config
import extension.configureAndroidKotlin
import extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<ApplicationExtension> {

                defaultConfig.apply {
                    targetSdk = Config.android.targetSdkVersion
                    minSdk = Config.android.minSdkVersion
                    versionCode = Config.android.versionCode
                    versionName = Config.android.versionName
                }

                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                        applicationIdSuffix = ".debug"
                    }
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        applicationIdSuffix = ".release"
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                buildFeatures.buildConfig = true



                dependencies {
                    add("implementation", versionCatalog().findLibrary("hilt-android").get())
                    add("implementation", versionCatalog().findLibrary("androidx-hilt-navigation-compose").get())
                    add("implementation", versionCatalog().findLibrary("kotlinx-serialization-json").get())
                    add("ksp", versionCatalog().findLibrary("hilt-compiler").get())
                    add("implementation", versionCatalog().findLibrary("arrow").get())
                    add("implementation", versionCatalog().findLibrary("coil").get())
                }
                configureAndroidKotlin(this)
            }
        }
    }
}