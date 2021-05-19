import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.32"
  id("org.jetbrains.compose") version "0.4.0-build182"
}

group = "com.cmota.playground.triathlon"
version = "1.0.1"

buildscript {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
    maven(url = "https://jetbrains.bintray.com/intellij-third-party-dependencies")
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    classpath("com.android.tools.build:gradle:7.0.0-beta01")
    classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.32")
    classpath("com.squareup.sqldelight:gradle-plugin:1.5.0")
  }
}

repositories {
  google()
  mavenCentral()
  maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
  implementation(compose.desktop.currentOs)

  implementation(project(":shared"))
}

tasks.withType<KotlinCompile>() {
  kotlinOptions.jvmTarget = "11"
}

compose.desktop {
  application {
    mainClass = "MainKt"
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "com.cmota.playground.triathlon"
      packageVersion = "1.0.1"
    }
  }
}