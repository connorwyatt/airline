package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.Serializable

@Serializable
data class SeatingConfiguration(
    val standard: Int,
    val businessClass: Int,
    val firstClass: Int,
) {
    val totalSeats: Int
        get() = standard + businessClass + firstClass
}
