package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.common.rabbitmq.kodein.bindCommandDefinition
import org.kodein.di.*

val aircraftMessagesCommandsDependenciesModule by
    DI.Module {
        bindCommandDefinition<AddAircraft>(AddAircraft.TYPE)
        bindCommandDefinition<RejectAircraft>(RejectAircraft.TYPE)
    }
