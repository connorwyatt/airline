package io.connorwyatt.airline.aircraft.service.domain

import io.connorwyatt.airline.aircraft.messages.commands.AircraftAdded
import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import io.connorwyatt.common.eventstore.aggregates.Aggregate

class AircraftAggregate(id: String) : Aggregate(id) {
    private val aircraftID = AircraftID.parse(id).getOrThrow()

    init {
        handle<AircraftAdded>(::apply)
    }

    fun addAircraft(
        registration: AircraftRegistration,
        manufacturer: String,
        model: String,
        seatingConfiguration: SeatingConfiguration,
    ) {
        raiseEvent(
            AircraftAdded(aircraftID, registration, manufacturer, model, seatingConfiguration)
        )
    }

    private fun apply(event: AircraftAdded) {}
}
