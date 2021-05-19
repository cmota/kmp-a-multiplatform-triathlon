plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}
group = "com.cmota.playground.triathlon"
version = "1.0.1"

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.2.0")

    implementation("androidx.compose.ui:ui:1.0.0-beta06")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta06")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta06")
    implementation("androidx.compose.material:material:1.0.0-beta06")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta06")

    implementation("androidx.activity:activity-compose:1.3.0-alpha07")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha10")

    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")

    implementation("com.russhwolf:multiplatform-settings:0.7.6")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.cmota.playground.triathlon"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 2
        versionName = "1.0.1"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta06"
    }
}