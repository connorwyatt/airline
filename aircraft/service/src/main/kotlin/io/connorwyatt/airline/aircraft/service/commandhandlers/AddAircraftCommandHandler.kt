package io.connorwyatt.airline.aircraft.service.commandhandlers

import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.common.rabbitmq.commandhandlers.CommandHandler

class AddAircraftCommandHandler : CommandHandler() {
    init {
        handle<AddAircraft>(::handle)
    }

    private fun handle(command: AddAircraft) {}
}
