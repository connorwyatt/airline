package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.common.configuration.loadConfigurationFromJsonFiles
import io.connorwyatt.common.server.ApplicationConfiguration
import io.connorwyatt.common.server.Server
import org.kodein.di.DI

fun main() {
    val configuration =
        loadConfigurationFromJsonFiles<Configuration>("configuration", "development")

    Server(
            8000,
            applicationConfiguration(configuration, listOf(applicationDependenciesModule())).build()
        )
        .start()
}

fun applicationConfiguration(configuration: Configuration, diModules: List<DI.Module>) =
    ApplicationConfiguration.Builder().apply {
        addDIModules(diModules)

        addRabbitMQ(configuration.rabbitMQ)

        configureRouting { addAircraftRoutes() }
    }
