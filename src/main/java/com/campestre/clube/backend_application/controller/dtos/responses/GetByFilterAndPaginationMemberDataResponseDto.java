package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class GetByFilterAndPaginationMemberDataResponseDto {
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private List<MemberDataResponseDto> items;

    public GetByFilterAndPaginationMemberDataResponseDto(Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages, List<MemberDataResponseDto> items) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.items = items;
    }

    public GetByFilterAndPaginationMemberDataResponseDto() {
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

    public List<MemberDataResponseDto> getItems() {
        return items;
    }

    public void setItems(List<MemberDataResponseDto> items) {
        this.items = items;
    }
}
