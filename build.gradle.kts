buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
//        classpath("com.gradleup.shadow:shadow-gradle-plugin:9.0.0-beta11")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.compose.compiler) apply false
    id ("com.gradleup.shadow") version "9.0.0-beta11" apply false
}