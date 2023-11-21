package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.airline.shared.ids.HashidGenerator
import io.connorwyatt.common.result.Result
import kotlinx.serialization.Serializable

@Serializable(with = AircraftIDSerializer::class)
class AircraftID private constructor(val value: String) {
    override fun equals(other: Any?): Boolean {
        if (other !is AircraftID) return false

        return value == other.value
    }

    override fun hashCode(): Int = value.hashCode()

    companion object {
        private const val PREFIX = "aircraft"

        fun parse(value: String): Result<AircraftID, ParseAircraftIDFailure> {
            val split = value.split(':')

            if (split.count() != 2) {
                return Result.Failure(ParseAircraftIDFailure.InvalidFormat)
            }

            if (split.component1() != PREFIX) {
                return Result.Failure(ParseAircraftIDFailure.InvalidPrefix)
            }

            return Result.Success(AircraftID(value))
        }

        fun random(): AircraftID = AircraftID("$PREFIX:${HashidGenerator.generate()}")

        interface ParseAircraftIDFailure {
            object InvalidPrefix : ParseAircraftIDFailure

            object InvalidFormat : ParseAircraftIDFailure
        }
    }
}
