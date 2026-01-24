import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization") version "2.1.0" // Usa la misma versión de tu Kotlin
}

kotlin {
    androidTarget()
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    val ktorVersion = "3.1.1"
    val kotlinxDatetime = "0.6.1"
    sourceSets {
        androidMain.dependencies {
        implementation("io.ktor:ktor-client-okhttp:${ktorVersion}")

    }
        commonMain.dependencies {
            api(libs.koin.core)
            implementation("io.ktor:ktor-client-core:${ktorVersion}")
            implementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
            implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:${kotlinxDatetime}")
            implementation("io.ktor:ktor-client-logging:${ktorVersion}")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:${ktorVersion}")

        }
    }
}

android {
    namespace = "com.valu.valulibrary.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
