plugins {
    application
    kotlin("jvm")
}

group = "com.cto51.blog.mob649e81673fa5.mdns.server"
version = "0.0.1"

application {
    mainClass.set("com.cto51.blog.mob649e81673fa5.mdns.server.MainKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation("org.jmdns:jmdns:3.6.1")
    implementation(project(":library"))
}
