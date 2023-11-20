package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AircraftRegistrationSerializer : KSerializer<AircraftRegistration> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AircraftRegistration", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: AircraftRegistration) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): AircraftRegistration {
        val string = decoder.decodeString()
        val result = AircraftRegistration.parse(string)
        return result.getOrThrow()
    }
}
