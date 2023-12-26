plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.ktor.plugin")
}

group = "com.github.connorwyatt.airline.aircraft.service"

application {
    mainClass.set("com.github.connorwyatt.airline.aircraft.service.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.aircraft.commands)
    implementation(projects.aircraft.data)
    implementation(projects.aircraft.events)
    implementation(projects.aircraft.models)

    implementation(common.configuration)
    implementation(common.eventstore)
    implementation(common.mongodb)
    implementation(common.optional)
    implementation(common.rabbitmq)
    implementation(common.result)
    implementation(common.server)
    implementation(common.validation)

    implementation(libraries.kodein.di)
    implementation(libraries.kodein.di.framework.ktor.server)
    implementation(libraries.ktor.server.core)
    implementation(libraries.logback.classic)
    implementation(libraries.logstash.logbackEncoder)
}
