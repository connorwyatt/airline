package io.connorwyatt.airline.aircraft.service.domain.commandhandlers

import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import io.connorwyatt.common.eventstore.aggregates.AggregatesRepository
import io.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

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
