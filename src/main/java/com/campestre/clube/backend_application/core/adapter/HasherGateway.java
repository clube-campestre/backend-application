package com.campestre.clube.backend_application.core.adapter;

public interface HasherGateway {
    String encrypt(String value);
    String decrypt(String value);
}
