plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.shadow)
    implementation(libs.plugin.detekt)
}