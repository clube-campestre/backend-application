package com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.unit.UnitEntityMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDataJpaAdapter implements MemberDataGateway {

    private final MemberDataJpaRepository repository;

    public MemberDataJpaAdapter(MemberDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    @Cacheable(cacheNames = "memberData.byCpf", key = "#cpf")
    public MemberData findByCpf(String cpf) {
        return MemberDataEntityMapper.toDomain(repository.findByCpf(cpf).get());
    }

    @Override
    @Cacheable(cacheNames = "memberData.list")
    public List<MemberData> findAll() {
        return MemberDataEntityMapper.toDomain(repository.findAll());
    }

    @Override
    @Cacheable(
            cacheNames = "memberData.byUnitNameAndUnitRole",
            key = "#unitName + '-' + #unitRole"
    )
    public List<MemberData> findByUnitNameAndUnitRole(String unitName, UnitRole unitRole) {
        return MemberDataEntityMapper.toDomain(repository.findByUnitSurnameAndUnitRole(unitName, unitRole));
    }

    @Override
    @Cacheable(
            cacheNames = "memberData.byUnitNameAndPagination",
            key = "#unitName + '-' + #pagination.pageNumber + '-' + #pagination.pageSize"
    )
    public List<MemberData> findByUnitNameAndPagination(String unitName, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByUnitSurnameAndPagination(
                        unitName, PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    @Cacheable(
            cacheNames = "memberData.byClassCategoryAndClassRole",
            key = "#classCategory + '-' + #classRole"
    )
    public List<MemberData> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole) {
        return MemberDataEntityMapper.toDomain(
                repository.findByClassCategoryAndClassRole(classCategory, classRole)
        );
    }

    @Override
    @Cacheable(
            cacheNames = "memberData.byClassAndPagination",
            key = "#classCategory + '-' + #pagination.pageNumber + '-' + #pagination.pageSize"
    )
    public List<MemberData> findByClassAndPagination(ClassCategory classCategory, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByClassAndPagination(
                        classCategory, PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    @Cacheable(
            cacheNames = "memberData.byFilterAndPagination",
            key = "#filter + '-' + #pagination.pageNumber + '-' + #pagination.pageSize"
    )
    public List<MemberData> findByFilterAndPagination(Filter filter, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByFilterAndPagination(
                        UnitEntityMapper.toEntity(filter.unit()),
                        filter.classCategory(),
                        filter.username(),
                        PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    @CacheEvict(cacheNames = {
            "memberData.byCpf", "memberData.list", "memberData.byUnitNameAndUnitRole",
            "memberData.byUnitNameAndPagination", "memberData.byClassCategoryAndClassRole",
            "memberData.byClassAndPagination", "memberData.byFilterAndPagination"
    }, allEntries = true)
    public MemberData save(MemberData memberData) {
        return MemberDataEntityMapper.toDomain(
                repository.save(MemberDataEntityMapper.toEntity(memberData))
        );
    }

    @Override
    @CacheEvict(cacheNames = {
            "memberData.byCpf", "memberData.list", "memberData.byUnitNameAndUnitRole",
            "memberData.byUnitNameAndPagination", "memberData.byClassCategoryAndClassRole",
            "memberData.byClassAndPagination", "memberData.byFilterAndPagination"
    }, allEntries = true)
    public void removeByCpf(String cpf) {
        repository.deleteById(cpf);
    }
}
