package com.github.connorwyatt.airline.shared.ids

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isNotEmpty

class HashidGeneratorTests {
    @Test
    fun `generates a hashid`() {
        val hashid = HashidGenerator.generate()

        expectThat(hashid).isNotEmpty()
    }
}
