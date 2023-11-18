package io.connorwyatt.airline.aircraft.messages.models

import java.util.UUID
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class AircraftIdSerializerTests {
    @Test
    fun `AircraftId can be serialized`() {
        val uuid = UUID.randomUUID()

        val serializedAircraftId =
            Json.encodeToString(AircraftIdSerializer, AircraftId.parse("aircraft:$uuid"))

        expectThat(serializedAircraftId).isEqualTo("\"aircraft:$uuid\"")
    }

    @Test
    fun `AircraftId can be deserialized`() {
        val uuid = UUID.randomUUID()

        val serializedAircraftId = "\"aircraft:$uuid\""
        val aircraftId = Json.decodeFromString(AircraftIdSerializer, serializedAircraftId)

        expectThat(aircraftId).isEqualTo(AircraftId.parse("aircraft:$uuid"))
    }
}
