plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
}

val java = rootProject.findProperty("javaVersion").toString().toIntOrNull()
    ?: throw IllegalStateException("Java version not specified")

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(java))
    }
}

kotlin {
    jvmToolchain(java)
}