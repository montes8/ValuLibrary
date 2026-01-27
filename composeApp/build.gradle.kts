import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20" // Usa tu versión de Kotlin
    id("kotlin-parcelize")
}

kotlin {
    @Suppress("DEPRECATION")
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("androidx.navigation:navigation-compose:2.9.6")
            implementation("com.google.android.play:app-update-ktx:2.1.0")
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.koin.compose)
            implementation(libs.koin.android)
            implementation("io.insert-koin:koin-compose-viewmodel:4.0.0")
            implementation("com.google.android.material:material:1.12.0")
            implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
            implementation(libs.kotlinx.serialization.core)
            implementation("io.ktor:ktor-client-okhttp:3.1.1")
            implementation(project(":uitaycompose"))
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
            implementation(projects.shared)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.valu.valulibrary"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.valu.valulibrary"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore/libraryvalu.jks")
            storePassword = "@libraryvalu2026"
            keyAlias = "LibraryValu"
            keyPassword = "@libraryvalu2026"
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

}

