package com.campestre.clube.backend_application.deprecated.service;

import com.campestre.clube.backend_application.deprecated.controller.dtos.EmailMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishEmail(String email, String code, String queueName) {
        EmailMessageDto emailMessage = new EmailMessageDto(email, code);
        rabbitTemplate.convertAndSend(queueName, emailMessage);
    }
}