package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.airline.aircraft.messages.models.AircraftId
import io.connorwyatt.common.rabbitmq.Command
import kotlinx.serialization.Serializable

@Serializable
data class AddAircraft(val id: AircraftId) : Command {
    companion object {
        const val TYPE = "AddAircraft"
    }
}
