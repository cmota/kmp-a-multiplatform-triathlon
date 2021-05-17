pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "triathlon"

include(":web")
include(":androidApp")
include(":shared")
