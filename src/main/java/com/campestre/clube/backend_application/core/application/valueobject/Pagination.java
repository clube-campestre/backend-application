package com.campestre.clube.backend_application.core.application.valueobject;

public class Pagination {
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;

    private Pagination(Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public Pagination of(Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages) {
        return new Pagination(pageNumber, pageSize, totalItems, totalPages);
    }

    public static Pagination of(Integer pageNumber, Integer pageSize) {
        return new Pagination(pageNumber, pageSize, 0L, 0);
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
}
