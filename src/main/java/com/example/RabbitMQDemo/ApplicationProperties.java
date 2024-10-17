package com.example.RabbitMQDemo;

import org.springframework.boot.context.properties.ConfigurationProperties;


    @ConfigurationProperties(prefix = "demo")
    public record ApplicationProperties(
           String eventsExchange,
           String newMessagesQueue) {}

