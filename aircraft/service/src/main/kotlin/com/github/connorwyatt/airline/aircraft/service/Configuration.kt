package com.github.connorwyatt.airline.aircraft.service

import com.github.connorwyatt.airline.aircraft.data.configuration.DataConfiguration
import com.github.connorwyatt.common.eventstore.configuration.EventStoreConfiguration
import com.github.connorwyatt.common.mongodb.configuration.MongoDBConfiguration
import com.github.connorwyatt.common.rabbitmq.configuration.RabbitMQConfiguration

data class Configuration(
    val data: DataConfiguration,
    val eventStore: EventStoreConfiguration,
    val mongoDB: MongoDBConfiguration,
    val rabbitMQ: RabbitMQConfiguration,
)
