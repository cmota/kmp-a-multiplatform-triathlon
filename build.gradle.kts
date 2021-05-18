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
        classpath("com.android.tools.build:gradle:7.0.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.32")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.0")
    }
}
group = "com.cmota.playground.triathlon"
version = "1.0.1"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://dl.bintray.com/ekito/koin")
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers/")
        maven(url = "https://jitpack.io")
    }
}
