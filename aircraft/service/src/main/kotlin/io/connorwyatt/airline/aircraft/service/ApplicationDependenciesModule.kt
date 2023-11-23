package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.data.aircraftDataDependenciesModule
import io.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import io.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import io.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesCommandsDependenciesModule
import io.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesEventsDependenciesModule
import io.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import io.connorwyatt.airline.aircraft.service.domain.commandhandlers.AddAircraftCommandHandler
import io.connorwyatt.airline.aircraft.service.domain.commandhandlers.RejectAircraftCommandHandler
import io.connorwyatt.airline.aircraft.service.domain.processmanagers.DuplicateAircraftProcessManager
import io.connorwyatt.common.eventstore.kodein.bindAggregateDefinition
import io.connorwyatt.common.eventstore.kodein.bindEventHandler
import io.connorwyatt.common.eventstore.streams.StreamDescriptor
import io.connorwyatt.common.rabbitmq.kodein.bindCommandHandler
import io.connorwyatt.common.rabbitmq.kodein.bindCommandQueueDefinition
import io.connorwyatt.common.rabbitmq.kodein.bindCommandRoutingRules
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.new

fun applicationDependenciesModule(configuration: Configuration): DI.Module =
    DI.Module(name = ::applicationDependenciesModule.name) {
        import(aircraftDataDependenciesModule(configuration.data, configuration.mongoDB))
        import(aircraftMessagesCommandsDependenciesModule)
        import(aircraftMessagesEventsDependenciesModule)

        bindAggregateDefinition("aircraft", ::AircraftAggregate)

        bindCommandQueueDefinition("commands")
        bindCommandRoutingRules { defaultQueue("commands") }
        bindCommandHandler<AddAircraft> { new(::AddAircraftCommandHandler) }
        bindCommandHandler<RejectAircraft> { new(::RejectAircraftCommandHandler) }

        bindEventHandler(setOf(StreamDescriptor.Category("aircraft"))) {
            new(::DuplicateAircraftProcessManager)
        }

        bindProvider { new(::AircraftDefinitionValidatorMapper) }
    }
