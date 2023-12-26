package com.github.connorwyatt.airline.aircraft.service.domain.commandhandlers

import com.github.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import com.github.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import com.github.connorwyatt.common.eventstore.aggregates.AggregatesRepository
import com.github.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

class AddAircraftCommandHandler(private val aggregatesRepository: AggregatesRepository) :
    CommandHandler() {
    init {
        handle<AddAircraft>(::handle)
    }

    private suspend fun handle(command: AddAircraft) {
        val aggregate = aggregatesRepository.load<AircraftAggregate>(command.aircraftID.value)

        aggregate.addAircraft(
            command.registration,
            command.manufacturer,
            command.model,
            command.seatingConfiguration
        )

        aggregatesRepository.save(aggregate)
    }
}
