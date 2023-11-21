package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.airline.shared.ids.HashidGenerator
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AircraftIdSerializerTests {
    @Test
    fun `AircraftId can be serialized`() {
        val hashid = HashidGenerator.generate()

        val serializedAircraftId =
            Json.encodeToString(
                AircraftIdSerializer,
                AircraftId.parse("aircraft:$hashid").getOrThrow()
            )

        expectThat(serializedAircraftId).isEqualTo("\"aircraft:$hashid\"")
    }

    @Test
    fun `AircraftId can be deserialized`() {
        val hashid = HashidGenerator.generate()

        val serializedAircraftId = "\"aircraft:$hashid\""
        val aircraftId = Json.decodeFromString(AircraftIdSerializer, serializedAircraftId)

        expectThat(aircraftId).isEqualTo(AircraftId.parse("aircraft:$hashid").getOrThrow())
    }
}
