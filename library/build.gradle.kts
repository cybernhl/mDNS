plugins {
    java
    kotlin("jvm")
    id ("com.gradleup.shadow") version "9.0.0-beta11"
}

group = "com.github.cybernhl"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8
    }
}

dependencies {
    implementation("org.jmdns:jmdns:3.6.1")
}
