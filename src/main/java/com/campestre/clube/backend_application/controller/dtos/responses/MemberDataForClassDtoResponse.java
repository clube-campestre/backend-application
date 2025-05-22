package com.campestre.clube.backend_application.controller.dtos.responses;

import java.util.List;

public class MemberDataForClassDtoResponse {
    private String instructorName;
    private List<MemberDataDtoResponse> members;

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public List<MemberDataDtoResponse> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDataDtoResponse> members) {
        this.members = members;
    }
}
