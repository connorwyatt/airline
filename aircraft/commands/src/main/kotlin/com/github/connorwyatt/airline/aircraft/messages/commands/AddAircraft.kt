package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftID
import com.github.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import com.github.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import com.github.connorwyatt.common.rabbitmq.Command
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
