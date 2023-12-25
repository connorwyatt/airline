package io.connorwyatt.airline.aircraft.service.domain

import io.connorwyatt.airline.aircraft.messages.commands.AircraftAdded
import io.connorwyatt.airline.aircraft.messages.commands.AircraftRejected
import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import io.connorwyatt.airline.aircraft.service.domain.exceptions.UnableToRejectException
import io.connorwyatt.common.eventstore.aggregates.Aggregate

class AircraftAggregate(id: String) : Aggregate(id) {
    private val aircraftID = AircraftID.parse(id).getOrThrow()

    private var status: AircraftStatus? = null

    init {
        handle<AircraftAdded>(::apply)
        handle<AircraftRejected>(::apply)
    }

    fun addAircraft(
        registration: AircraftRegistration,
        manufacturer: String,
        model: String,
        seatingConfiguration: SeatingConfiguration,
    ) {
        if (status != null) {
            return
        }

        raiseEvent(
            AircraftAdded(aircraftID, registration, manufacturer, model, seatingConfiguration)
        )
    }

    fun rejectAircraft(reason: String) {
        if (status == AircraftStatus.Rejected) {
            return
        }

        if (status != AircraftStatus.Added) {
            throw UnableToRejectException("Status must be ${AircraftStatus.Added}.")
        }

        raiseEvent(AircraftRejected(aircraftID, reason))
    }

    private fun apply(event: AircraftAdded) {
        status = AircraftStatus.Added
    }

    private fun apply(event: AircraftRejected) {
        status = AircraftStatus.Rejected
    }

    enum class AircraftStatus {
        Added,
        Rejected,
    }
}
