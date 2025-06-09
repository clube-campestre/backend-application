package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class UnitResetedResponseDto {
    private List<String> reseted;
    private List<String> notReseted;

    public UnitResetedResponseDto(List<String> reseted, List<String> notReseted) {
        this.reseted = reseted;
        this.notReseted = notReseted;
    }

    public UnitResetedResponseDto() {
    }

    public List<String> getReseted() {
        return reseted;
    }

    public void setReseted(List<String> reseted) {
        this.reseted = reseted;
    }

    public List<String> getNotReseted() {
        return notReseted;
    }

    public void setNotReseted(List<String> notReseted) {
        this.notReseted = notReseted;
    }
}
