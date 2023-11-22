package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import io.connorwyatt.common.eventstore.events.Event
import io.connorwyatt.common.eventstore.events.VersionedEventType
import kotlinx.serialization.Serializable

@Serializable
data class AircraftAdded(
    val aircraftID: AircraftID,
    val registration: AircraftRegistration,
    val manufacturer: String,
    val model: String,
    val seatingConfiguration: SeatingConfiguration,
) : Event {
    companion object {
        val TYPE = VersionedEventType("aircraft.aircraftAdded", 1)
    }
}
