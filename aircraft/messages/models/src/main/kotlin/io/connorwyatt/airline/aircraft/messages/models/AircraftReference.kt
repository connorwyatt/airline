package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.Serializable

@Serializable data class AircraftReference(val aircraftID: AircraftID)
