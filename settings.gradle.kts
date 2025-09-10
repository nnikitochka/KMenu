enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "KMenu"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

project("api")
project("common")
project("platform")

platform("paper")

fun project(projectName: String) {
    include("${rootProject.name.lowercase()}-$projectName")
}

fun platform(platformName: String) {
    include("${rootProject.name.lowercase()}-platform:$platformName")
}