package com.campestre.clube.backend_application.entity.models;

import com.campestre.clube.backend_application.entity.MemberData;

import java.util.List;

public class MemberDataForClass {
    private String instructorName;
    private Pagination pagination;
    private List<MemberData> members;

    public MemberDataForClass(String instructorName, Pagination pagination, List<MemberData> members) {
        this.instructorName = instructorName;
        this.pagination = pagination;
        this.members = members;
    }

    public MemberDataForClass() {
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<MemberData> getMembers() {
        return members;
    }

    public void setMembers(List<MemberData> members) {
        this.members = members;
    }
}
