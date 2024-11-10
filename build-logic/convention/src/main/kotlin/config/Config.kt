package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    val android = AndroidConfig(
        minSdkVersion = 24,
        targetSdkVersion = 34,
        compileSdkVersion = 34,
        versionCode = 1,
        versionName = "1.0",
    )

    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_21,
        kotlinJvm = JvmTarget.JVM_21,
        freeCompilerArgs = listOf()
    )
}


data class AndroidConfig(
    val minSdkVersion: Int,
    val targetSdkVersion: Int,
    val compileSdkVersion: Int,
    val versionCode: Int,
    val versionName: String,
)


data class JvmConfig(
    val javaVersion: JavaVersion,
    val kotlinJvm: JvmTarget,
    val freeCompilerArgs: List<String>
)