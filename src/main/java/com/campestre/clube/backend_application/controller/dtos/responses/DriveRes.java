package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.Objects;

public class DriveRes {
    private String id;
    private int status;
    private String message;
    private String url;

    public DriveRes(int status, String message, String url, String id) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.url = url;
    }

    public DriveRes() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Método equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriveRes driveRes = (DriveRes) o;
        return status == driveRes.status &&
                Objects.equals(message, driveRes.message) &&
                Objects.equals(url, driveRes.url);
    }

    // Método hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(status, message, url);
    }
}
