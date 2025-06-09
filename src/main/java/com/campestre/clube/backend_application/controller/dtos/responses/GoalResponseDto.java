package com.campestre.clube.backend_application.controller.dtos.responses;

import com.campestre.clube.backend_application.entity.Tag;

public class GoalResponseDto {
    private Double totalPrice;
    private Tag tag;

    public GoalResponseDto(Double totalPrice, Tag tag) {
        this.totalPrice = totalPrice;
        this.tag = tag;
    }

    public GoalResponseDto() {
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
