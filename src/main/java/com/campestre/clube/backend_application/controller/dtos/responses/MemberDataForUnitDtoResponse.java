package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class MemberDataForUnitDtoResponse {
    private Integer score;
    private String counselorName;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private List<MemberDataResponseDto> members;

    public MemberDataForUnitDtoResponse(Integer score, String counselorName, Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages, List<MemberDataResponseDto> members) {
        this.score = score;
        this.counselorName = counselorName;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.members = members;
    }

    public MemberDataForUnitDtoResponse() {
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }

    public List<MemberDataResponseDto> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDataResponseDto> members) {
        this.members = members;
    }
}
