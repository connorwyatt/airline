package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.common.rabbitmq.Command
import kotlinx.serialization.Serializable

@Serializable
data class RejectAircraft(val aircraftID: AircraftID, val reason: String) : Command {
    companion object {
        const val TYPE = "RejectAircraft"
    }
}
