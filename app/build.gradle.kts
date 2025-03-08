import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    id("kotlin-parcelize")
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.app.tasks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.tasks"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("../keystores/debug.keystore")
            storePassword = "android"
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(libs.versions.jvmTarget.get()))
        }
    }

    compileOptions {
        sourceCompatibility(libs.versions.jvmTarget.get())
        targetCompatibility(libs.versions.jvmTarget.get())
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE*"
        }
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        // Required for Robolectric
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.core)

    // Lifecycle
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.material3.windowsize)

    implementation(libs.navigation.compose)

    implementation(libs.constraintlayout.compose)

    implementation(libs.activity.compose)

    // Coroutine
    implementation(libs.coroutines.android)

    // Kotlin serialization
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.collections.immutable)

    // Log
    implementation(libs.timber)

    // Work
    implementation(libs.work.runtime)

    // Splash Screen
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.appcompat)

    // Database
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room)

    // Shimmer Loading
    implementation(libs.shimmer.loading)

    // Dependency Management
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler)

    // Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.junit.junit5)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.robolectric.robolectric)

    // Android Test
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}

// Apply the git hooks setup script
apply(from = "$rootDir/scripts/git-hook.gradle.kts")

tasks {
    withType<Test> {
        useJUnitPlatform()
        maxHeapSize = "2g"
        maxParallelForks = Runtime.getRuntime().availableProcessors()
        jvmArgs = jvmArgs.orEmpty() + "-XX:+UseParallelGC"
        android.sourceSets["main"].res.srcDirs("src/test/res")
    }
}
