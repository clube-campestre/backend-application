package com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata;

import com.campestre.clube.backend_application.core.domain.Unit;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberDataJpaRepository extends JpaRepository<MemberDataEntity, String> {
    boolean existsByCpf(String cpf);

    Optional<MemberDataEntity> findByCpf(@CPF String cpf);
    List<MemberDataEntity> findByUnitIdAndUnitRole(Integer unitId, UnitRole unitRole);
    List<MemberDataEntity> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole);

    @Query("""
                SELECT m FROM MemberDataEntity m
                WHERE (:unit IS NULL OR m.unit = :unit)
                  AND (:classCategory IS NULL OR m.classCategory = :classCategory)
                  AND (:username IS NULL OR LOWER(m.username) LIKE LOWER(CONCAT('%', :username, '%')))
                ORDER BY m.username ASC
            """)
    Page<MemberDataEntity> findByFilterAndPagination(
            @Param("unit") Unit unit,
            @Param("classCategory") ClassCategory classCategory,
            @Param("username") String username,
            Pageable pageable
    );

    @Query("""
                SELECT m FROM MemberDataEntity m
                WHERE (:unitId IS NULL OR m.unit.id = :unitId)
                ORDER BY m.username ASC
            """)
    Page<MemberDataEntity> findByUnitIdAndPagination(
            @Param("unitId") Integer unitId,
            Pageable pageable
    );

    @Query("""
                SELECT m FROM MemberDataEntity m
                WHERE (:classCategory IS NULL OR m.classCategory = :classCategory)
                ORDER BY m.username ASC
            """)
    Page<MemberDataEntity> findByClassAndPagination(
            @Param("classCategory") ClassCategory classCategory,
            Pageable pageable
    );
}
