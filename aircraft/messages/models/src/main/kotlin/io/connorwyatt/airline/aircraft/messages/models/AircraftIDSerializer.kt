package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AircraftIDSerializer : KSerializer<AircraftID> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AircraftID", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: AircraftID) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): AircraftID {
        val string = decoder.decodeString()
        val result = AircraftID.parse(string)
        return result.getOrThrow()
    }
}
