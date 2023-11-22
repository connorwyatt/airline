package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.airline.shared.ids.HashidGenerator
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class AircraftIDTests {
    @Test
    fun `AircraftID can be parsed and the value can be retrieved`() {
        val hashid = HashidGenerator.generate()

        val id =
            expectCatching { AircraftID.parse("aircraft:${hashid}").getOrThrow() }
                .isSuccess()
                .subject

        expectThat(id.value).isEqualTo("aircraft:${hashid}")
    }

    @Test
    fun `random AircraftID can be generated and the value can be parsed`() {
        val id = expectCatching { AircraftID.random() }.isSuccess().subject

        expectCatching { AircraftID.parse(id.value).getOrThrow() }.isSuccess()
    }

    @Test
    fun `AircraftIDs with the same value are equal`() {
        val hashid = HashidGenerator.generate()

        val id1 = AircraftID.parse("aircraft:$hashid")
        val id2 = AircraftID.parse("aircraft:$hashid")

        expectThat(id1).isEqualTo(id2)
    }
}
