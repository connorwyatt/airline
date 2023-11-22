package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import io.connorwyatt.common.rabbitmq.Command
import kotlinx.serialization.Serializable

@Serializable
data class AddAircraft(
    val aircraftID: AircraftID,
    val registration: AircraftRegistration,
    val manufacturer: String,
    val model: String,
    val seatingConfiguration: SeatingConfiguration,
) : Command {
    companion object {
        const val TYPE = "AddAircraft"
    }
}
