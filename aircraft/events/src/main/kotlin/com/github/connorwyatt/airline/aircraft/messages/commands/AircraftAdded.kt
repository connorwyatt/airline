package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftID
import com.github.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import com.github.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import com.github.connorwyatt.common.eventstore.events.Event
import com.github.connorwyatt.common.eventstore.events.VersionedEventType
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
