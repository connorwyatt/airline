package io.connorwyatt.airline.aircraft.service.domain.commandhandlers

import io.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import io.connorwyatt.common.eventstore.aggregates.AggregatesRepository
import io.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

class RejectAircraftCommandHandler(private val aggregatesRepository: AggregatesRepository) :
    CommandHandler() {
    init {
        handle<RejectAircraft>(::handle)
    }

    private fun handle(command: RejectAircraft) {}
}
