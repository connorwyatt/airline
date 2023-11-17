package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.common.server.ApplicationConfiguration
import io.connorwyatt.common.server.Server

fun main() {
    Server(8000, applicationConfiguration().build()).start()
}

fun applicationConfiguration() =
    ApplicationConfiguration.Builder().apply { configureRouting { addAircraftRoutes() } }
