package com.campestre.clube.backend_application.infrastructure.web.dtos.memberdata;

public class UpdateMemberUnitAndClassRequestDto {
    private String unitName;
    private String unitRole;
    private String classCategory;
    private String classRole;

    public UpdateMemberUnitAndClassRequestDto() {}

    public String getUnitRole() {
        return unitRole;
    }

    public void setUnitRole(String unitRole) {
        this.unitRole = unitRole;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public String getClassRole() {
        return classRole;
    }

    public void setClassRole(String classRole) {
        this.classRole = classRole;
    }
}
