package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import org.antlr.v4.runtime.misc.Pair;
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

    public List<Pair<String, String>> getAllUnits() {
        return Arrays.stream(UnitEnum.values()).map(unitEnum ->
                new Pair<>(unitEnum.name(), unitEnum.getFormattedValue())
        ).toList();
    }

    public List<String> getAllUnitsRole() {
        return Arrays.stream(UnitRole.values()).map(Enum::name).toList();
    }
}
