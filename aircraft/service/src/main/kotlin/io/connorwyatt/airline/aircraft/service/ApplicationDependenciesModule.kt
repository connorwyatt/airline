package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesCommandsDependenciesModule
import io.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesEventsDependenciesModule
import io.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import io.connorwyatt.airline.aircraft.service.domain.commandhandlers.AddAircraftCommandHandler
import io.connorwyatt.common.eventstore.kodein.bindAggregateDefinition
import io.connorwyatt.common.rabbitmq.kodein.bindCommandHandler
import io.connorwyatt.common.rabbitmq.kodein.bindCommandQueueDefinition
import io.connorwyatt.common.rabbitmq.kodein.bindCommandRoutingRules
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.new

fun applicationDependenciesModule(): DI.Module =
    DI.Module(name = ::applicationDependenciesModule.name) {
        import(aircraftMessagesCommandsDependenciesModule)
        import(aircraftMessagesEventsDependenciesModule)

        bindAggregateDefinition("aircraft", ::AircraftAggregate)

        bindCommandQueueDefinition("commands")
        bindCommandRoutingRules { defaultQueue("commands") }
        bindCommandHandler<AddAircraft> { new(::AddAircraftCommandHandler) }

        bindProvider { new(::AircraftDefinitionValidatorMapper) }
    }
