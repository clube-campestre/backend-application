package com.campestre.clube.backend_application.core.application.statement.valueobject;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;

import java.time.Instant;

public class Filter{
    private Instant startDate;
    private Instant endDate;
    private Long tagId;
    private TransactionType type;
    private String description;

    private Filter(Instant startDate, Instant endDate, Long tagId, TransactionType type, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tagId = tagId;
        this.type = type;
        this.description = description;
    }

    public Filter() {}

    public static Filter of(String startDate, String endDate, Long tagId, TransactionType type, String description) {
        Instant startDateFormated = null;
        Instant endDateFormated = null;
        if (startDate != null && !startDate.isBlank()) startDateFormated = Instant.parse(startDate);
        if (startDate != null && !endDate.isBlank()) endDateFormated = Instant.parse(endDate);
        return new Filter(startDateFormated, endDateFormated, tagId, type, description);
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Long getTagId() {
        return tagId;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
