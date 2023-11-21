package io.connorwyatt.airline.aircraft.messages.models

import io.connorwyatt.airline.shared.ids.HashidGenerator
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class AircraftIdTests {
    @Test
    fun `AircraftId can be parsed and the value can be retrieved`() {
        val hashid = HashidGenerator.generate()

        val id =
            expectCatching { AircraftId.parse("aircraft:${hashid}").getOrThrow() }
                .isSuccess()
                .subject

        expectThat(id.value).isEqualTo("aircraft:${hashid}")
    }

    @Test
    fun `random AircraftId can be generated and the value can be parsed`() {
        val id = expectCatching { AircraftId.random() }.isSuccess().subject

        expectCatching { AircraftId.parse(id.value).getOrThrow() }.isSuccess()
    }

    @Test
    fun `AircraftIds with the same value are equal`() {
        val hashid = HashidGenerator.generate()

        val id1 = AircraftId.parse("aircraft:$hashid")
        val id2 = AircraftId.parse("aircraft:$hashid")

        expectThat(id1).isEqualTo(id2)
    }
}
