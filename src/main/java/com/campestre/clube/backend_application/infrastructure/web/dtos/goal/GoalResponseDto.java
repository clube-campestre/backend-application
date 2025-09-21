package com.campestre.clube.backend_application.infrastructure.web.dtos.goal;

import com.campestre.clube.backend_application.core.domain.Tag;

import java.math.BigDecimal;

public class GoalResponseDto {
    private BigDecimal totalPrice;
    private Tag tag;

    public GoalResponseDto(BigDecimal totalPrice, Tag tag) {
        this.totalPrice = totalPrice;
        this.tag = tag;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
