package com.campestre.clube.backend_application.deprecated.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queuename}")
    private String queueName;

    @Bean
    public Queue myQueue() {
        return new Queue(queueName, true);
    }
}