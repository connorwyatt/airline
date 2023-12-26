package com.github.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AircraftRegistrationSerializerTests {
    @Test
    fun `AircraftRegistration can be serialized`() {
        val serializedAircraftRegistration =
            Json.encodeToString(
                AircraftRegistrationSerializer,
                AircraftRegistration.parse("G-ABCD").getOrThrow()
            )

        expectThat(serializedAircraftRegistration).isEqualTo("\"G-ABCD\"")
    }

    @Test
    fun `AircraftRegistration can be deserialized`() {
        val serializedAircraftRegistration = "\"G-ABCD\""
        val aircraftID =
            Json.decodeFromString(AircraftRegistrationSerializer, serializedAircraftRegistration)

        expectThat(aircraftID).isEqualTo(AircraftRegistration.parse("G-ABCD").getOrThrow())
    }
}
