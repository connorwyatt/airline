package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.Serializable

@Serializable
data class SeatingConfiguration(
    val standard: Int = 0,
    val businessClass: Int = 0,
    val firstClass: Int = 0
) {
    val totalSeats: Int
        get() = standard + businessClass + firstClass
}
