package plugin

import com.android.build.api.dsl.ApplicationExtension
import config.Config
import extension.configureAndroidKotlin
import extension.versionCatalog
import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project


class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            val keystorePropertiesFile = rootProject.file("keystore.properties")
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.dagger.hilt.android")
            }

            val versionPropsFile = rootProject.file("app/versions.properties")
            val versionProps = Properties()
            versionPropsFile.inputStream().use { stream ->
                versionProps.load(stream)
            }

            // Define version components
            val versionMajor = versionProps.getProperty("version_major").toInt()
            val versionMinor = versionProps.getProperty("version_minor").toInt()
            val versionPatch = versionProps.getProperty("version_patch").toInt()

            extensions.configure<ApplicationExtension> {

                defaultConfig.apply {
                    targetSdk = Config.android.targetSdkVersion
                    minSdk = Config.android.minSdkVersion
                    versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
                    versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
                }
                signingConfigs {
                    create("release") {
                        storeFile = file("../keystore/release.keystore")
                        storePassword = keystoreProperties["RELEASE_KEY_STORE_PASSWORD"] as String
                        keyAlias = keystoreProperties["RELEASE_KEY_ALIAS"] as String
                        keyPassword = keystoreProperties["RELEASE_KEY_PASSWORD"] as String
                    }
                    getByName("debug") {
                        storeFile = file("../keystore/debug.keystore")
                        storePassword = keystoreProperties["DEBUG_KEY_STORE_PASSWORD"] as String
                        keyAlias = keystoreProperties["DEBUG_KEY_ALIAS"] as String
                        keyPassword = keystoreProperties["DEBUG_KEY_PASSWORD"] as String
                    }
                }
                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                        applicationIdSuffix = ".debug"
                        signingConfig = signingConfigs.getByName("debug")
                        resValue("string", "app_name", "Dev The Movie DB")
                    }
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        signingConfig = signingConfigs.getByName("release")
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                        resValue("string", "app_name", "The Movie DB")
                    }
                }

                buildFeatures.buildConfig = true



                dependencies {
                    add("implementation", (project(":core:common")))
                    add("implementation", versionCatalog().findLibrary("hilt-android").get())
                    add(
                        "implementation",
                        versionCatalog().findLibrary("androidx-hilt-navigation-compose").get()
                    )
                    add(
                        "implementation",
                        versionCatalog().findLibrary("kotlinx-serialization-json").get()
                    )
                    add("ksp", versionCatalog().findLibrary("hilt-compiler").get())
                    add("implementation", versionCatalog().findLibrary("arrow").get())
                    add("implementation", versionCatalog().findLibrary("coil").get())
                }
                configureAndroidKotlin(this)
            }
        }
    }
}