package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.airline.aircraft.messages.models.AircraftDefinitionRequest
import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftReference
import io.connorwyatt.common.rabbitmq.CommandEnvelope
import io.connorwyatt.common.rabbitmq.bus.CommandBus
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI

fun Routing.addAircraftRoutes() {
    post("/aircraft") {
        val validatorMapper by call.closestDI().instance<AircraftDefinitionValidatorMapper>()
        val bus by call.closestDI().instance<CommandBus>()

        val request = call.receive<AircraftDefinitionRequest>()

        // TODO: Handle validation errors
        val definition = validatorMapper.validateAndMap(request).getOrThrow()

        val aircraftID = AircraftID.random()

        bus.send(
            CommandEnvelope(
                AddAircraft(
                    aircraftID,
                    definition.registration,
                    definition.manufacturer,
                    definition.model,
                    definition.seatingConfiguration
                )
            )
        )

        call.respond(HttpStatusCode.Accepted, AircraftReference(aircraftID))
    }
}
