pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url ="https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "web-compose"

include(":shared")
project(":shared").projectDir = file("../shared")
