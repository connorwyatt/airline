package com.github.connorwyatt.airline.aircraft.service.domain.commandhandlers

import com.github.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import com.github.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import com.github.connorwyatt.common.eventstore.aggregates.AggregatesRepository
import com.github.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

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
