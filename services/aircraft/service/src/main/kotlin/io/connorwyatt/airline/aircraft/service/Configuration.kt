package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.common.rabbitmq.configuration.RabbitMQConfiguration

data class Configuration(
    val rabbitMQ: RabbitMQConfiguration,
)
