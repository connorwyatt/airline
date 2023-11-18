package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.airline.aircraft.messages.models.AircraftId
import io.connorwyatt.common.rabbitmq.CommandEnvelope
import io.connorwyatt.common.rabbitmq.bus.CommandBus
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Routing.addAircraftRoutes() {
    post("/aircraft") {
        val bus by call.closestDI().instance<CommandBus>()

        bus.send(CommandEnvelope(AddAircraft(AircraftId.random())))

        call.respond(HttpStatusCode.Accepted)
    }
}
