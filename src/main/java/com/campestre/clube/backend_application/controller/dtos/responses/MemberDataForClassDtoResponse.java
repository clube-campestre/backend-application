package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class MemberDataForClassDtoResponse {
    private String instructorName;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private List<MemberDataResponseDto> members;

    public MemberDataForClassDtoResponse(String instructorName, Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages, List<MemberDataResponseDto> members) {
        this.instructorName = instructorName;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.members = members;
    }

    public MemberDataForClassDtoResponse() {
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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<MemberDataResponseDto> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDataResponseDto> members) {
        this.members = members;
    }
}
