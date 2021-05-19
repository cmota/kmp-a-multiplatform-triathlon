import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}
group = "com.cmota.playground.triathlon"
version = "1.0.1"

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

sqldelight {
    database("ConferenceDb") {
        packageName = "data"
    }
}

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }

    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    android()

    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = "shared"

        ios.deploymentTarget = "13.2"
    }

    js(IR) {
        browser()
        binaries.executable()
    }

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.5.4")
                implementation("io.ktor:ktor-client-json:1.5.4")
                implementation("io.ktor:ktor-client-serialization:1.5.4")
                implementation("io.ktor:ktor-client-logging:1.5.4")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.1.0")

                implementation("com.squareup.sqldelight:runtime:1.5.0")

                implementation("com.russhwolf:multiplatform-settings:0.7.6")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.preference:preference:1.1.1")

                implementation("io.ktor:ktor-client-android:1.5.4")
                implementation("io.ktor:ktor-client-okhttp:1.4.0")

                implementation("com.squareup.sqldelight:android-driver:1.5.0")
            }
        }
        /*val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }*/

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.4")

                implementation("com.squareup.sqldelight:native-driver:1.5.0")
            }
        }
        val iosTest by getting

        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:1.5.4")

                //Not published at the moment: https://github.com/cashapp/sqldelight/issues/1667
                //implementation("com.squareup.sqldelight:sqljs-driver:1.4.3")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:1.5.4")
                implementation("io.ktor:ktor-client-core-jvm:1.5.4")
                implementation("io.ktor:ktor-client-serialization-jvm:1.5.4")
            }
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)