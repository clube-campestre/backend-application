package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EnumerationService {

    public List<String> getAllClassesCategory() {
        return Arrays.stream(ClassCategory.values()).map(Enum::name).toList();
    }

    public List<String> getAllClassesRole() {
        return Arrays.stream(ClassRole.values()).map(Enum::name).toList();
    }

    public List<String> getAllUnits() {
        return Arrays.stream(UnitEnum.values()).map(Enum::name).toList();
    }

    public List<String> getAllUnitsRole() {
        return Arrays.stream(UnitRole.values()).map(Enum::name).toList();
    }
}
