package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftID
import com.github.connorwyatt.common.rabbitmq.Command
import kotlinx.serialization.Serializable

@Serializable
data class RejectAircraft(val aircraftID: AircraftID, val reason: String) : Command {
    companion object {
        const val TYPE = "RejectAircraft"
    }
}
