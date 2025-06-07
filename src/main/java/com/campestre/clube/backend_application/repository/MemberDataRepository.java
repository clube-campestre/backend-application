package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.MemberData;
import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberDataRepository extends JpaRepository<MemberData, String> {
    boolean existsByCpf(String cpf);
    Optional<MemberData> findByCpf(@CPF String cpf);
    List<MemberData> findByUnitIdAndUnitRole(Integer unitId, UnitRole unitRole);
    List<MemberData> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole);

    @Query("""
                SELECT m FROM member_data m
                WHERE (:unit IS NULL OR m.unit = :unit)
                  AND (:classCategory IS NULL OR m.classCategory = :classCategory)
                  AND (:username IS NULL OR LOWER(m.username) LIKE LOWER(CONCAT('%', :username, '%')))
                ORDER BY m.username ASC
            """)
    Page<MemberData> findByFilterAndPagination(
            @Param("unit") Unit unit,
            @Param("classCategory") ClassCategory classCategory,
            @Param("username") String username,
            Pageable pageable
    );

    @Query("""
                SELECT m FROM member_data m
                WHERE (:unitId IS NULL OR m.unit.id = :unitId)
                ORDER BY m.username ASC
            """)
    Page<MemberData> findByUnitAndPagination(
            @Param("unitId") Integer unitId,
            Pageable pageable
    );

    @Query("""
                SELECT m FROM member_data m
                WHERE (:classCategory IS NULL OR m.classCategory = :classCategory)
                ORDER BY m.username ASC
            """)
    Page<MemberData> findByClassAndPagination(
            @Param("classCategory") ClassCategory classCategory,
            Pageable pageable
    );
}
