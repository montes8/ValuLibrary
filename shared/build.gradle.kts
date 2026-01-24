import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    kotlin("plugin.serialization") version "2.1.0"
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)// Usa la misma versión de tu Kotlin
    id("com.github.gmazzo.buildconfig")
}

buildConfig {
    //packageName.set("com.tu.app")
    // Define las variables que necesitas
    buildConfigField("String", "BASE_URL_SERVICE", "\"servertayrelease.onrender.com\"")
    buildConfigField("String", "BASE_URL_SERVICE_DEV", "\"servertay.onrender.com\"")
    // Esta lógica detecta si la tarea de Gradle actual es de 'Debug'
    val isDebug = project.gradle.startParameter.taskNames.any { it.contains("Debug", ignoreCase = true) }

    // Creamos el campo booleano vinculado a la variante
    buildConfigField("Boolean", "DEBUG", isDebug.toString())
}

kotlin {
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
            implementation("androidx.datastore:datastore-preferences:1.1.7")

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
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

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}