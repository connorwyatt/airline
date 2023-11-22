package io.connorwyatt.airline.aircraft.messages.commands

import io.connorwyatt.common.eventstore.kodein.bindEventDefinition
import org.kodein.di.DI

val aircraftMessagesEventsDependenciesModule by
    DI.Module { bindEventDefinition<AircraftAdded>(AircraftAdded.TYPE) }
