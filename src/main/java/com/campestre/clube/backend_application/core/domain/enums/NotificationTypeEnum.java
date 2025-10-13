package com.campestre.clube.backend_application.core.domain.enums;


import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.ERROR_NOTIFICATION_TYPE_ENUM;

public enum NotificationTypeEnum {
    RESET_PASSWORD_EMAIL;

    public static NotificationTypeEnum fromString(String value) {
        return EnumUtils.fromString(NotificationTypeEnum.class, value, ERROR_NOTIFICATION_TYPE_ENUM);
    }
}
