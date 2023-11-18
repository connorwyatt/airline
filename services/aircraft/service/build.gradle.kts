plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.ktor.plugin")
}

group = "io.connorwyatt.airline.aircraft.service"

application {
    mainClass.set("io.connorwyatt.airline.aircraft.service.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(":services:aircraft:messages:commands"))
    implementation(project(":services:aircraft:messages:models"))

    implementation(common.configuration)
    implementation(common.rabbitmq)
    implementation(common.server)

    implementation(libraries.kodein.di)
    implementation(libraries.kodein.di.framework.ktor.server)
    implementation(libraries.ktor.server.core)
    implementation(libraries.logback.classic)
    implementation(libraries.logstash.logbackEncoder)
}