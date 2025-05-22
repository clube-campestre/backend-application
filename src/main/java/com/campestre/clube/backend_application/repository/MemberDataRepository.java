package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.MedicalData;
import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.enums.ClassCategory;
import com.campestre.clube.backend_application.entity.enums.ClassRole;
import com.campestre.clube.backend_application.entity.enums.UnitRole;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberDataRepository extends JpaRepository<MemberData, String> {
    boolean existsByCpf(String cpf);
    Optional<MemberData> findByCpf(@CPF String cpf);

    List<MemberData> findByUnitIdAndUnitRoleNot(Integer unitId, UnitRole unitRole);
    List<MemberData> findByUnitIdAndUnitRole(Integer unitId, UnitRole unitRole);

    List<MemberData> findByClassCategoryAndClassRoleNot(ClassCategory classCategory, ClassRole classRole);
    List<MemberData> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole);
}
