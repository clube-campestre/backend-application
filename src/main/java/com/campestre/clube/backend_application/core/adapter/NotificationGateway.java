package com.campestre.clube.backend_application.core.adapter;

public interface NotificationGateway {
    void sendEmail(String to, String subject, String text);
}
