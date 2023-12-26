package com.github.connorwyatt.airline.aircraft.messages.models

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftRegistration.Companion.ParseAircraftRegistrationError
import com.github.connorwyatt.common.result.Result
import java.util.stream.Stream
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class AircraftRegistrationTests {
    @Test
    fun `a valid AircraftRegistration can be parsed and the value can be retrieved`() {
        val id =
            expectCatching { AircraftRegistration.parse("G-ABCD").getOrThrow() }.isSuccess().subject

        expectThat(id.value).isEqualTo("G-ABCD")
    }

    internal class InvalidAircraftRegistrationArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> =
            Stream.of(
                Arguments.of("G-TOOLONG", ParseAircraftRegistrationError.InvalidLength),
                Arguments.of("FORMAT", ParseAircraftRegistrationError.InvalidFormat),
            )
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidAircraftRegistrationArgumentsProvider::class)
    fun `an invalid AircraftRegistration cannot be parsed`(
        input: String,
        expectedError: ParseAircraftRegistrationError
    ) {
        expectThat(AircraftRegistration.parse(input)).isEqualTo(Result.Failure(expectedError))
    }

    @Test
    fun `AircraftRegistrations with the same value are equal`() {
        val id1 = AircraftRegistration.parse("G-ABCD")
        val id2 = AircraftRegistration.parse("G-ABCD")

        expectThat(id1).isEqualTo(id2)
    }
}
