plugins {
    application
    kotlin("jvm")
    id ("com.gradleup.shadow") version "9.0.0-beta11"
}
apply(plugin = "java")

group = "com.51cto.blog.mob649e81673fa5.mdns.discovery"
version = "0.0.1"

application {
    mainClass.set("com.cto51.blog.mob649e81673fa5.mdns.discovery.MainKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation("org.jmdns:jmdns:3.6.1")
    implementation(project(":library"))
}
