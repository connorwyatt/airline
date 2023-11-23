package io.connorwyatt.airline.aircraft.data.mongodb

import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.connorwyatt.airline.aircraft.data.DuplicateAircraftRepository
import io.connorwyatt.airline.aircraft.data.mongodb.models.DuplicateAircraftDocument
import io.connorwyatt.airline.aircraft.messages.models.AircraftID
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.common.eventstore.eventhandlers.EventHandler.Cursor
import io.connorwyatt.common.eventstore.mongodbmodels.CursorDocument
import io.connorwyatt.common.mongodb.collectionName
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.toList

class MongoDBDuplicateAircraftRepository(
    private val mongoClient: MongoClient,
    private val databaseName: String
) : DuplicateAircraftRepository {
    override suspend fun getAircraftIDForRegistration(
        registration: AircraftRegistration
    ): AircraftID? {
        return aircraftCollection()
            .find(Filters.eq(DuplicateAircraftDocument::registration.name, registration.value))
            .showRecordId(false)
            .singleOrNull()
            ?.aircraftID
            ?.let { AircraftID.parse(it).getOrThrow() }
    }

    override suspend fun upsertAircraft(
        aircraftID: AircraftID,
        registration: AircraftRegistration
    ) {
        aircraftCollection()
            .replaceOne(
                Filters.eq(DuplicateAircraftDocument::aircraftID.name, aircraftID.value),
                DuplicateAircraftDocument(aircraftID.value, registration.value),
                ReplaceOptions().upsert(true)
            )
    }

    override suspend fun getStreamPosition(subscriptionName: String, streamName: String): Long? =
        cursorsCollection()
            .find(
                Filters.and(
                    Filters.eq(CursorDocument::subscriptionName.name, subscriptionName),
                    Filters.eq(CursorDocument::streamName.name, streamName),
                )
            )
            .singleOrNull()
            ?.lastHandledStreamPosition

    override suspend fun updateStreamPosition(cursor: Cursor) {
        cursorsCollection().apply {
            val hasCursor =
                find(
                        Filters.and(
                            Filters.eq(
                                CursorDocument::subscriptionName.name,
                                cursor.subscriptionName
                            ),
                            Filters.eq(CursorDocument::streamName.name, cursor.streamName),
                        )
                    )
                    .limit(1)
                    .toList()
                    .isNotEmpty()

            val mongoDBCursor = CursorDocument.fromCursor(cursor)

            if (hasCursor) {
                replaceOne(
                    Filters.and(
                        Filters.eq(CursorDocument::subscriptionName.name, cursor.subscriptionName),
                        Filters.eq(CursorDocument::streamName.name, cursor.streamName),
                    ),
                    mongoDBCursor
                )
            } else {
                insertOne(mongoDBCursor)
            }
        }
    }

    private fun aircraftCollection() =
        mongoClient
            .getDatabase(databaseName)
            .getCollection<DuplicateAircraftDocument>(collectionName<DuplicateAircraftDocument>())

    private fun cursorsCollection() =
        mongoClient
            .getDatabase(databaseName)
            .getCollection<CursorDocument>(collectionName<CursorDocument>())
}
