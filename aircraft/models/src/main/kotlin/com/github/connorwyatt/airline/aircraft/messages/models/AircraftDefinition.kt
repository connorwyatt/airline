package com.github.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.Serializable

@Serializable
data class AircraftDefinition(
    val registration: AircraftRegistration,
    val manufacturer: String,
    val model: String,
    val seatingConfiguration: SeatingConfiguration,
)
