package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class MemberDataForUnitDtoResponse {
    private Integer score;
    private String counselorName;
    private List<MemberDataResponseDto> members;

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
