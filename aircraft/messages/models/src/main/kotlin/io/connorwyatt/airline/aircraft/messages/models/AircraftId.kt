package io.connorwyatt.airline.aircraft.messages.models

import java.util.*
import kotlinx.serialization.Serializable

@Serializable(with = AircraftIdSerializer::class)
class AircraftId private constructor(private val value: String) {
    override fun toString(): String = "$PREFIX:$value"

    override fun equals(other: Any?): Boolean {
        if (other !is AircraftId) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        private const val PREFIX = "aircraft"

        fun parse(value: String): AircraftId {
            val split = value.split(':')

            if (split.count() != 2) {
                throw IllegalArgumentException("AircraftId must be in the format of $PREFIX:<UUID>")
            }

            return AircraftId(split.component2())
        }

        fun random(): AircraftId = AircraftId(UUID.randomUUID().toString())
    }
}
