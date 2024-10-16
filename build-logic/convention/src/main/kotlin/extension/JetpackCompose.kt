package extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *, *>
) {
    extension.apply {
        buildFeatures.compose = true
        val libs = versionCatalog()
        dependencies {

            add("implementation", platform(versionCatalog().findLibrary("androidx-compose-bom").get()))
            add("implementation", libs.findLibrary("androidx-compose-foundation").get())
            add("implementation", libs.findLibrary("androidx-ui").get())
            add("implementation", libs.findLibrary("androidx-ui-graphics").get())
            add("implementation", libs.findLibrary("androidx-ui-tooling").get())
            add("implementation", libs.findLibrary("androidx-ui-tooling-preview").get())
            add("implementation", libs.findLibrary("androidx-material3").get())
            add("implementation", libs.findLibrary("androidx-navigation-compose").get())

            add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
            add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
            add("debugImplementation", libs.findLibrary("androidx-ui-tooling").get())
            add("debugImplementation", libs.findLibrary("androidx-ui-test-manifest").get())
            add("androidTestImplementation", libs.findLibrary("androidx-ui-test-junit4").get())
        }
    }
}