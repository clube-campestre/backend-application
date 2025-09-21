package com.campestre.clube.backend_application.core.domain;

import java.math.BigDecimal;

public class Goal {
    private BigDecimal totalPrice;
    private Tag tag;

    private Goal(BigDecimal totalPrice, Tag tag) {
        this.totalPrice = totalPrice;
        this.tag = tag;
    }

    public static Goal of(BigDecimal totalPrice, Tag tag) {
        return new Goal(totalPrice, tag);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Tag getTag() {
        return tag;
    }
}
