package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.common.eventstore.events.Event
import io.connorwyatt.common.eventstore.events.VersionedEventType
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
