package com.campestre.clube.backend_application.core.adapter;

public interface NotificationGateway {
    void sendResetPasswordEmail(String to, String code);
}
