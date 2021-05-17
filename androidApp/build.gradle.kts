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
    implementation("com.google.android.material:material:1.3.0")
    implementation("com.google.android.gms:play-services-auth:19.0.0")

    implementation("androidx.appcompat:appcompat:1.2.0")

    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    implementation("com.russhwolf:multiplatform-settings:0.7.6")
}
android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.cmota.playground.triathlon.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
}