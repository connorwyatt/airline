package io.connorwyatt.airline.aircraft.data.mongodb.models

import io.connorwyatt.common.mongodb.CollectionName
import org.bson.codecs.pojo.annotations.BsonId

@CollectionName("duplicateAircraft")
internal data class DuplicateAircraftDocument(
    val aircraftID: String,
    val registration: String,
    @BsonId val _id: String = aircraftID,
)
