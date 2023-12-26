package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftID
import com.github.connorwyatt.common.eventstore.events.Event
import com.github.connorwyatt.common.eventstore.events.VersionedEventType
import kotlinx.serialization.Serializable

@Serializable
data class AircraftRejected(
    val aircraftID: AircraftID,
    val reason: String,
) : Event {
    companion object {
        val TYPE = VersionedEventType("aircraft.aircraftRejected", 1)
    }
}
