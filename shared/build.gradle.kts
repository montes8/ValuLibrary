
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization") version "2.1.0"
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    id("com.github.gmazzo.buildconfig")
}

buildConfig {
    buildConfigField("String", "BASE_URL_SERVICE", "\"servertay.onrender.com\"")
    buildConfigField("String", "BASE_URL_SERVICE_DEV", "\"servertay.onrender.com\"")
    val isDebug = project.gradle.startParameter.taskNames.any { it.contains("Debug", ignoreCase = true) }

    buildConfigField("Boolean", "DEBUG", isDebug.toString())
}

kotlin {
    @Suppress("DEPRECATION")
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
        implementation(libs.ktor.client.okhttp)

    }
        commonMain.dependencies {
            api(libs.koin.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.client.logging)
            implementation(libs.androidx.datastore.preferences)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

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
    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}