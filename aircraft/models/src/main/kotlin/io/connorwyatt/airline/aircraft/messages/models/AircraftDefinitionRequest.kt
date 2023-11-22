package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.Serializable

@Serializable
data class AircraftDefinitionRequest(
    val registration: String? = null,
    val manufacturer: String? = null,
    val model: String? = null,
    val seatingConfiguration: SeatingConfigurationRequest? = null,
) {
    @Serializable
    data class SeatingConfigurationRequest(
        val standard: Int? = null,
        val businessClass: Int? = null,
        val firstClass: Int? = null,
    )
}
