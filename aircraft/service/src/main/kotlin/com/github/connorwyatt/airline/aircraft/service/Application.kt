package com.github.connorwyatt.airline.aircraft.service

import com.github.connorwyatt.common.configuration.loadConfigurationFromJsonFiles
import com.github.connorwyatt.common.server.ApplicationConfiguration
import com.github.connorwyatt.common.server.Server
import org.kodein.di.DI

fun main() {
    val configuration =
        loadConfigurationFromJsonFiles<Configuration>("configuration", "development")

    Server(
            8000,
            applicationConfiguration(
                    configuration,
                    listOf(applicationDependenciesModule(configuration))
                )
                .build()
        )
        .start()
}

fun applicationConfiguration(configuration: Configuration, diModules: List<DI.Module>) =
    ApplicationConfiguration.Builder().apply {
        addDIModules(diModules)

        addEventStore(configuration.eventStore)
        addMongoDB(configuration.mongoDB)
        addRabbitMQ(configuration.rabbitMQ)

        configureRouting { addAircraftRoutes() }
    }
