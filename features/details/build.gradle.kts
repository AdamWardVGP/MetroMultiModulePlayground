plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {

    androidLibrary {
        namespace = "com.example.homelist"
        compileSdk = 36
        minSdk = 28

        withHostTestBuilder {
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.compose.material.icons)
            implementation(libs.kotlin.stdlib)

            implementation(project(":data"))

            implementation(libs.metrox.viewmodel)
            implementation(libs.metrox.android)

            implementation(libs.androidx.navigation.compose)
            implementation(libs.kotlinx.serialization.json)
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

}