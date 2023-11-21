package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.airline.shared.ids.HashidGenerator
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AircraftIDSerializerTests {
    @Test
    fun `AircraftID can be serialized`() {
        val hashid = HashidGenerator.generate()

        val serializedAircraftID =
            Json.encodeToString(
                AircraftIDSerializer,
                AircraftID.parse("aircraft:$hashid").getOrThrow()
            )

        expectThat(serializedAircraftID).isEqualTo("\"aircraft:$hashid\"")
    }

    @Test
    fun `AircraftID can be deserialized`() {
        val hashid = HashidGenerator.generate()

        val serializedAircraftID = "\"aircraft:$hashid\""
        val aircraftID = Json.decodeFromString(AircraftIDSerializer, serializedAircraftID)

        expectThat(aircraftID).isEqualTo(AircraftID.parse("aircraft:$hashid").getOrThrow())
    }
}
