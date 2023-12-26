package com.github.connorwyatt.airline.aircraft.service.domain.processmanagers

import com.github.connorwyatt.airline.aircraft.data.DuplicateAircraftRepository
import com.github.connorwyatt.airline.aircraft.messages.commands.AircraftAdded
import com.github.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import com.github.connorwyatt.common.eventstore.eventhandlers.EventHandler
import com.github.connorwyatt.common.eventstore.eventhandlers.SubscriptionName
import com.github.connorwyatt.common.eventstore.events.EventMetadata
import com.github.connorwyatt.common.eventstore.streams.StreamDescriptor
import com.github.connorwyatt.common.rabbitmq.CommandEnvelope
import com.github.connorwyatt.common.rabbitmq.bus.CommandBus

@SubscriptionName("duplicate-aircraft-process-manager")
class DuplicateAircraftProcessManager(
    private val repository: DuplicateAircraftRepository,
    private val bus: CommandBus
) : EventHandler() {
    init {
        handle<AircraftAdded>(::handleEvent)
    }

    private suspend fun handleEvent(event: AircraftAdded, metadata: EventMetadata) {
        if (repository.getAircraftIDForRegistration(event.registration) != null) {
            bus.send(CommandEnvelope(RejectAircraft(event.aircraftID, REASON)))
            return
        }

        repository.upsertAircraft(event.aircraftID, event.registration)
    }

    override suspend fun streamPosition(
        subscriptionName: String,
        streamDescriptor: StreamDescriptor
    ): Long? {
        return repository.getStreamPosition(subscriptionName, streamDescriptor.streamName)
    }

    override suspend fun updateStreamPosition(cursor: Cursor) {
        repository.updateStreamPosition(cursor)
    }

    companion object {
        private const val REASON = "An aircraft with this registration already exists."
    }
}
