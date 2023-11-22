package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.common.result.Result
import kotlinx.serialization.Serializable

@Serializable(with = AircraftRegistrationSerializer::class)
class AircraftRegistration private constructor(val value: String) {
    override fun equals(other: Any?): Boolean {
        if (other !is AircraftRegistration) return false

        return value == other.value
    }

    override fun hashCode(): Int = value.hashCode()

    companion object {
        // Only handles UK registrations
        private val regex = Regex("^G-[A-Z0-9]{4}$")

        fun parse(value: String): Result<AircraftRegistration, ParseAircraftRegistrationError> {
            if (value.length != 6) {
                return Result.Failure(ParseAircraftRegistrationError.InvalidLength)
            }

            if (!value.matches(regex)) {
                return Result.Failure(ParseAircraftRegistrationError.InvalidFormat)
            }

            return Result.Success(AircraftRegistration(value))
        }

        interface ParseAircraftRegistrationError {
            object InvalidLength : ParseAircraftRegistrationError

            object InvalidFormat : ParseAircraftRegistrationError
        }
    }
}
