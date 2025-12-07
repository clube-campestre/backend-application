package com.campestre.clube.backend_application.infrastructure.web.dtos.notification;

import com.campestre.clube.backend_application.core.domain.enums.NotificationTypeEnum;

import java.io.Serializable;

public class ResetPasswordEmailDto implements Serializable {
    private NotificationTypeEnum notificationType;
    private String email;
    private String code;

    public ResetPasswordEmailDto() {}

    public ResetPasswordEmailDto(NotificationTypeEnum notificationType, String email, String code) {
        this.notificationType = notificationType;
        this.email = email;
        this.code = code;
    }

    public NotificationTypeEnum getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationTypeEnum notificationType) {
        this.notificationType = notificationType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
