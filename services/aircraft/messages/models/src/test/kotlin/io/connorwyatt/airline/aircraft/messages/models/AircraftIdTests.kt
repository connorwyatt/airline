package io.connorwyatt.airline.aircraft.messages.models

import java.util.UUID
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class AircraftIdTests {
    @Test
    fun `AircraftId can be parsed and stringified`() {
        val uuid = UUID.randomUUID()

        val id = expectCatching { AircraftId.parse("aircraft:${uuid}") }.isSuccess().subject

        expectThat(id.toString()).isEqualTo("aircraft:${uuid}")
    }

    @Test
    fun `random AircraftId can be generated and stringified`() {
        val id = expectCatching { AircraftId.random() }.isSuccess().subject

        expectCatching { id.toString() }.isSuccess()
    }

    @Test
    fun `AircraftIds with the same value are equal`() {
        val uuid = UUID.randomUUID()

        val id1 = AircraftId.parse("aircraft:$uuid")
        val id2 = AircraftId.parse("aircraft:$uuid")

        expectThat(id1).isEqualTo(id2)
    }
}
