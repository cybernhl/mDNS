plugins {
    application
    kotlin("jvm")
}

apply(plugin = "java")
apply(plugin = "com.gradleup.shadow")

group = "com.cto51.blog.mob649e81673fa5.mdns.server"
version = "0.0.1"

application {
    mainClass.set("com.cto51.blog.mob649e81673fa5.mdns.server.MainKt")

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
