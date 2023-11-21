package io.connorwyatt.airline.aircraft.messages.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AircraftIdSerializer : KSerializer<AircraftId> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AircraftId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: AircraftId) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): AircraftId {
        val string = decoder.decodeString()
        val result = AircraftId.parse(string)
        return result.getOrThrow()
    }
}
