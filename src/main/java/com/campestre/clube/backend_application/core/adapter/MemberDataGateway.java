package com.campestre.clube.backend_application.core.adapter;

import com.campestre.clube.backend_application.core.application.memberdata.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;

import java.util.List;

public interface MemberDataGateway {
    boolean existsByCpf(String cpf);

    MemberData findByCpf(String cpf);
    List<MemberData> findAll();
    List<MemberData> findByUnitIdAndUnitRole(Integer id, UnitRole unitRole);
    List<MemberData> findByUnitAndPagination(Integer id, Pagination pagination);
    List<MemberData> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole);
    List<MemberData> findByClassAndPagination(ClassCategory classCategory, Pagination pagination);
    List<MemberData> findByFilterAndPagination(Filter filter, Pagination pagination);

    MemberData save(MemberData memberData);

    void removeByCpf(String cpf);
}
