package io.connorwyatt.airline.aircraft.data

import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.common.eventstore.eventhandlers.EventHandler.Cursor

interface DuplicateAircraftRepository {
    suspend fun getAircraftIDForRegistration(registration: AircraftRegistration): AircraftID?

    suspend fun upsertAircraft(aircraftID: AircraftID, registration: AircraftRegistration)

    suspend fun getStreamPosition(subscriptionName: String, streamName: String): Long?

    suspend fun updateStreamPosition(cursor: Cursor)
}
