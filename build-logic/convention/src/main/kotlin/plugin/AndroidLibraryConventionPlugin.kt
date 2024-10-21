package plugin

import com.android.build.api.dsl.LibraryExtension
import extension.configureAndroidKotlin
import extension.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidLibraryConventionPlugin: Plugin<Project> {

    override fun apply(project: Project) {

        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<LibraryExtension> {

                buildFeatures.buildConfig = true

                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                    }
                }

                dependencies {
                    add("implementation", (project(":core:common")))
                    add("implementation", versionCatalog().findLibrary("hilt-android").get())
                    add("implementation", versionCatalog().findLibrary("androidx-hilt-navigation-compose").get())
                    add("ksp", versionCatalog().findLibrary("hilt-compiler").get())
                    add("implementation", versionCatalog().findLibrary("kotlinx-serialization-json").get())
                    add("implementation", versionCatalog().findLibrary("arrow").get())
                }
                configureAndroidKotlin(this)
            }
        }
    }

}