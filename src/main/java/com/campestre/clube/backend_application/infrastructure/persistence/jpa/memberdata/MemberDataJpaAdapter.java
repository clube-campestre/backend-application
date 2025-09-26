package com.campestre.clube.backend_application.infrastructure.persistence.jpa.memberdata;

import com.campestre.clube.backend_application.core.adapter.MemberDataGateway;
import com.campestre.clube.backend_application.core.application.memberdata.valueobject.Filter;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.core.domain.enums.ClassRole;
import com.campestre.clube.backend_application.core.domain.enums.UnitRole;
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
    public MemberData findByCpf(String cpf) {
        return MemberDataEntityMapper.toDomain(repository.findByCpf(cpf).get());
    }

    @Override
    public List<MemberData> findAll() {
        return MemberDataEntityMapper.toDomain(repository.findAll());
    }

    @Override
    public List<MemberData> findByUnitNameAndUnitRole(String unitName, UnitRole unitRole) {
        return MemberDataEntityMapper.toDomain(repository.findByUnitSurnameAndUnitRole(unitName, unitRole));
    }

    @Override
    public List<MemberData> findByUnitNameAndPagination(String unitName, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByUnitSurnameAndPagination(
                        unitName, PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    public List<MemberData> findByClassCategoryAndClassRole(ClassCategory classCategory, ClassRole classRole) {
        return MemberDataEntityMapper.toDomain(
                repository.findByClassCategoryAndClassRole(classCategory, classRole)
        );
    }

    @Override
    public List<MemberData> findByClassAndPagination(ClassCategory classCategory, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByClassAndPagination(
                        classCategory, PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    public List<MemberData> findByFilterAndPagination(Filter filter, Pagination pagination) {
        return MemberDataEntityMapper.toDomain(
                repository.findByFilterAndPagination(
                        filter.unit(),
                        filter.classCategory(),
                        filter.username(),
                        PageRequest.of(pagination.getPageNumber(), pagination.getPageSize())
                ).getContent()
        );
    }

    @Override
    public MemberData save(MemberData memberData) {
        return MemberDataEntityMapper.toDomain(
                repository.save(MemberDataEntityMapper.toEntity(memberData))
        );
    }

    @Override
    public void removeByCpf(String cpf) {
        repository.deleteById(cpf);
    }
}
