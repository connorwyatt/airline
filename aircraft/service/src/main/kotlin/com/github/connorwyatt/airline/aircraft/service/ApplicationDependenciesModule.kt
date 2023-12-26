package com.github.connorwyatt.airline.aircraft.service

import com.github.connorwyatt.airline.aircraft.data.aircraftDataDependenciesModule
import com.github.connorwyatt.airline.aircraft.messages.commands.AddAircraft
import com.github.connorwyatt.airline.aircraft.messages.commands.RejectAircraft
import com.github.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesCommandsDependenciesModule
import com.github.connorwyatt.airline.aircraft.messages.commands.aircraftMessagesEventsDependenciesModule
import com.github.connorwyatt.airline.aircraft.service.domain.AircraftAggregate
import com.github.connorwyatt.airline.aircraft.service.domain.commandhandlers.AddAircraftCommandHandler
import com.github.connorwyatt.airline.aircraft.service.domain.commandhandlers.RejectAircraftCommandHandler
import com.github.connorwyatt.airline.aircraft.service.domain.processmanagers.DuplicateAircraftProcessManager
import com.github.connorwyatt.common.eventstore.kodein.bindAggregateDefinition
import com.github.connorwyatt.common.eventstore.kodein.bindEventHandler
import com.github.connorwyatt.common.eventstore.streams.StreamDescriptor
import com.github.connorwyatt.common.rabbitmq.kodein.bindCommandHandler
import com.github.connorwyatt.common.rabbitmq.kodein.bindCommandQueueDefinition
import com.github.connorwyatt.common.rabbitmq.kodein.bindCommandRoutingRules
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
