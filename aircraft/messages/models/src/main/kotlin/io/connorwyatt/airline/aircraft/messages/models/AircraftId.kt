package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.common.result.Result
import java.util.*
import kotlinx.serialization.Serializable

@Serializable(with = AircraftIdSerializer::class)
class AircraftId private constructor(val value: String) {
    override fun equals(other: Any?): Boolean {
        if (other !is AircraftId) return false

        return value == other.value
    }

    override fun hashCode(): Int = value.hashCode()

    companion object {
        private const val PREFIX = "aircraft"

        fun parse(value: String): Result<AircraftId, ParseAircraftIdFailure> {
            val split = value.split(':')

            if (split.count() != 2) {
                return Result.Failure(ParseAircraftIdFailure.InvalidFormat)
            }

            if (split.component1() != PREFIX) {
                return Result.Failure(ParseAircraftIdFailure.InvalidPrefix)
            }

            return Result.Success(AircraftId(value))
        }

        fun random(): AircraftId = AircraftId("$PREFIX:${UUID.randomUUID()}")

        interface ParseAircraftIdFailure {
            object InvalidPrefix : ParseAircraftIdFailure

            object InvalidFormat : ParseAircraftIdFailure
        }
    }
}
