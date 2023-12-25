package io.connorwyatt.airline.aircraft.service.domain.commandhandlers

import io.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import io.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import io.connorwyatt.common.eventstore.aggregates.AggregatesRepository
import io.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

class RejectAircraftCommandHandler(private val aggregatesRepository: AggregatesRepository) :
    CommandHandler() {
    init {
        handle<RejectAircraft>(::handle)
    }

    private suspend fun handle(command: RejectAircraft) {
        val aggregate = aggregatesRepository.load<AircraftAggregate>(command.aircraftID.value)

        aggregate.rejectAircraft(command.reason)

        aggregatesRepository.save(aggregate)
    }
}
