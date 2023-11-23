package io.connorwyatt.airline.aircraft.service.domain.processmanagers

import io.connorwyatt.airline.aircraft.data.DuplicateAircraftRepository
import io.connorwyatt.airline.aircraft.messages.commands.AircraftAdded
import io.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import io.connorwyatt.common.eventstore.eventhandlers.EventHandler
import io.connorwyatt.common.eventstore.eventhandlers.SubscriptionName
import io.connorwyatt.common.eventstore.events.EventMetadata
import io.connorwyatt.common.eventstore.streams.StreamDescriptor
import io.connorwyatt.common.rabbitmq.CommandEnvelope
import io.connorwyatt.common.rabbitmq.bus.CommandBus

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
            bus.send(CommandEnvelope(RejectAircraft(event.aircraftID)))
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
}
