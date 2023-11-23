package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.data.configuration.DataConfiguration
import io.connorwyatt.common.eventstore.configuration.EventStoreConfiguration
import io.connorwyatt.common.mongodb.configuration.MongoDBConfiguration
import io.connorwyatt.common.rabbitmq.configuration.RabbitMQConfiguration

data class Configuration(
    val data: DataConfiguration,
    val eventStore: EventStoreConfiguration,
    val mongoDB: MongoDBConfiguration,
    val rabbitMQ: RabbitMQConfiguration,
)
