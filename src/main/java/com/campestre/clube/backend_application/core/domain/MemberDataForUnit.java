package com.campestre.clube.backend_application.core.domain;

import java.util.List;

public class MemberDataForUnit {
    private Integer score;
    private String counselorName;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private List<MemberData> members;

    private MemberDataForUnit(
            Integer score, String counselorName, Integer pageNumber, Integer pageSize, Long totalItems,
            Integer totalPages, List<MemberData> members
    ) {
        this.score = score;
        this.counselorName = counselorName;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.members = members;
    }

    public static MemberDataForUnit of(
            Integer score, String counselorName, Integer pageNumber, Integer pageSize, Long totalItems,
            Integer totalPages, List<MemberData> members
    ) {
        return new MemberDataForUnit(score, counselorName, pageNumber, pageSize, totalItems, totalPages, members);
    }

    public Integer getScore() {
        return score;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public List<MemberData> getMembers() {
        return members;
    }
}
