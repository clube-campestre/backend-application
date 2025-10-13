package com.campestre.clube.backend_application.infrastructure.notification;

import com.campestre.clube.backend_application.core.adapter.NotificationGateway;
import com.campestre.clube.backend_application.core.domain.enums.NotificationTypeEnum;
import com.campestre.clube.backend_application.infrastructure.web.dtos.notification.ResetPasswordEmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Component
public class RabbitMQService implements NotificationGateway {
    private final RabbitTemplate rabbitTemplate;
    private final String resetPasswordQueueName;

    public RabbitMQService(
            RabbitTemplate rabbitTemplate,
            @Value("${rabbitmq.queuename.resetpassword}") String resetPasswordQueueName
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.resetPasswordQueueName = resetPasswordQueueName;
    }

    @Override
    public void sendResetPasswordEmail(String to, String code) {
        rabbitTemplate.convertAndSend(
                resetPasswordQueueName, new ResetPasswordEmailDto(NotificationTypeEnum.RESET_PASSWORD_EMAIL, to, code)
        );
    }
}
