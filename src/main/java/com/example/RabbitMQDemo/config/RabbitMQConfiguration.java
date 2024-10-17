package com.example.RabbitMQDemo.config;

import com.example.RabbitMQDemo.ApplicationProperties;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


@Configuration
public class RabbitMQConfiguration {

    private final ApplicationProperties properties;

    public RabbitMQConfiguration(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(properties.eventsExchange());
    }

    @Bean
    Queue newMessagesQueue() {
        return QueueBuilder.durable(properties.newMessagesQueue()).build();
    }

    @Bean
    Binding newMessagesQueueBinding() {
        return BindingBuilder.bind(newMessagesQueue()).to(exchange()).with(properties.newMessagesQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }
}
