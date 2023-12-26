package com.github.connorwyatt.airline.aircraft.data.mongodb.models

import com.github.connorwyatt.common.mongodb.CollectionName
import org.bson.codecs.pojo.annotations.BsonId

@CollectionName("duplicateAircraft")
internal data class DuplicateAircraftDocument(
    val aircraftID: String,
    val registration: String,
    @BsonId val _id: String = aircraftID,
)
