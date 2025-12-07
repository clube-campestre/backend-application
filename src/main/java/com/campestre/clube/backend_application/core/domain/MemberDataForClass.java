package com.campestre.clube.backend_application.core.domain;

import java.util.List;

public class MemberDataForClass {
    private String instructorName;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;
    private List<MemberData> members;

    private MemberDataForClass(
            String instructorName, Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages,
            List<MemberData> members
    ) {
        this.instructorName = instructorName;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.members = members;
    }

    public MemberDataForClass() {}

    public static MemberDataForClass of(
            String instructorName, Integer pageNumber, Integer pageSize, Long totalItems, Integer totalPages,
            List<MemberData> members
    ) {
        return new MemberDataForClass(instructorName, pageNumber, pageSize, totalItems, totalPages, members);
    }

    public String getInstructorName() {
        return instructorName;
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
