package com.github.connorwyatt.airline.aircraft.messages.commands

import com.github.connorwyatt.common.eventstore.kodein.bindEventDefinition
import org.kodein.di.DI

val aircraftMessagesEventsDependenciesModule by
    DI.Module {
        bindEventDefinition<AircraftAdded>(AircraftAdded.TYPE)
        bindEventDefinition<AircraftRejected>(AircraftRejected.TYPE)
    }
