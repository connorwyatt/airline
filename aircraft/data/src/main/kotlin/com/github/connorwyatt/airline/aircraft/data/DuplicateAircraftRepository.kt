package com.github.connorwyatt.airline.aircraft.data

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftID
import com.github.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import com.github.connorwyatt.common.eventstore.eventhandlers.EventHandler.Cursor

interface DuplicateAircraftRepository {
    suspend fun getAircraftIDForRegistration(registration: AircraftRegistration): AircraftID?

    suspend fun upsertAircraft(aircraftID: AircraftID, registration: AircraftRegistration)

    suspend fun getStreamPosition(subscriptionName: String, streamName: String): Long?

    suspend fun updateStreamPosition(cursor: Cursor)
}
