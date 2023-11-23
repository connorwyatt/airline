package io.connorwyatt.airline.aircraft.data.inmemory

import io.connorwyatt.airline.aircraft.data.DuplicateAircraftRepository
import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.common.eventstore.eventhandlers.EventHandler.Cursor

class InMemoryDuplicateAircraftRepository : DuplicateAircraftRepository {
    private var registrationLookup = mapOf<AircraftID, AircraftRegistration>()
    private var cursors = listOf<Cursor>()

    override suspend fun getAircraftIDForRegistration(
        registration: AircraftRegistration
    ): AircraftID? {
        return registrationLookup.entries.singleOrNull { it.value == registration }?.key
    }

    override suspend fun upsertAircraft(
        aircraftID: AircraftID,
        registration: AircraftRegistration
    ) {
        if (registrationLookup.contains(aircraftID)) {
            registrationLookup =
                registrationLookup.mapValues {
                    if (it.key == aircraftID) registration else it.value
                }
        } else {
            registrationLookup = registrationLookup.plus(aircraftID to registration)
        }
    }

    override suspend fun getStreamPosition(subscriptionName: String, streamName: String): Long? =
        cursors
            .singleOrNull { it.subscriptionName == subscriptionName && it.streamName == streamName }
            ?.lastHandledStreamPosition

    override suspend fun updateStreamPosition(cursor: Cursor) {
        val hasCursor = cursors.contains(cursor)

        if (!hasCursor) {
            cursors = cursors.plus(cursor)
            return
        }

        cursors = cursors.map { if (it == cursor) cursor else it }
    }
}
