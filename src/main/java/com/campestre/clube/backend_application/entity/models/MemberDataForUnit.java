package com.campestre.clube.backend_application.entity.models;

import com.campestre.clube.backend_application.entity.MemberData;

import java.util.List;

public class MemberDataForUnit {
    private Integer score;
    private String counselorName;
    private Pagination pagination;
    private List<MemberData> members;

    public MemberDataForUnit(Integer score, String counselorName, Pagination pagination, List<MemberData> members) {
        this.score = score;
        this.counselorName = counselorName;
        this.pagination = pagination;
        this.members = members;
    }

    public MemberDataForUnit() {
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
