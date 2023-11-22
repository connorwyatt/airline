package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.common.eventstore.configuration.EventStoreConfiguration
import io.connorwyatt.common.rabbitmq.configuration.RabbitMQConfiguration

data class Configuration(
    val eventStore: EventStoreConfiguration,
    val rabbitMQ: RabbitMQConfiguration,
)
