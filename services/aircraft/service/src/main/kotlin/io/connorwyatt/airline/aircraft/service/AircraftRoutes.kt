package io.connorwyatt.airline.aircraft.service

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.addAircraftRoutes() {
    get("/aircraft") { call.respond(HttpStatusCode.OK) }
}
