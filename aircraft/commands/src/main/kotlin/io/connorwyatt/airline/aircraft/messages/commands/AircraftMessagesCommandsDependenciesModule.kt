package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.common.rabbitmq.kodein.bindCommandDefinition
import org.kodein.di.*

val aircraftMessagesCommandsDependenciesModule by
    DI.Module {
        bindCommandDefinition<AddAircraft>(AddAircraft.TYPE)
        bindCommandDefinition<RejectAircraft>(RejectAircraft.TYPE)
    }
