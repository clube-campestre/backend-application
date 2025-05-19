package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.StatementRepository;
import com.campestre.clube.backend_application.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private TagRepository tagRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Statement register(Statement statement, Integer idTag){
        Tag tag = tagRepository.findById(idTag)
                .orElseThrow(() -> new NotFoundException("Tag by id [%s] not found".formatted(idTag)));

        if(statementRepository.existsByInformationAndPriceAndTransactionDateAndTag(
                statement.getInformation(), statement.getPrice(), statement.getTransactionDate(), tag)
        ){
            throw new ConflictException("Statement with existing information, price, transaction_date and tag");
        }
        statement.setTag(tag);
        return statementRepository.save(statement);
    }

    public List<Statement> getAll(){
        return statementRepository.findAll();
    }

    public Statement getById(Integer id){
        return validateStatementExists(id);
    }

    public Statement update(StatementRequestDto dto, Integer id){
        Statement existingStatement = validateStatementExists(id);

        Tag tag = tagRepository.findById(dto.getIdTag())
                .orElseThrow(() -> new NotFoundException("Tag by id [%s] not found".formatted(dto.getIdTag())));// Aqui você resolve a tag real

        existingStatement.setInformation(dto.getInformation());
        existingStatement.setPrice(dto.getPrice());
        existingStatement.setTransactionDate(dto.getTransactionDate());
        existingStatement.setTransactionType(dto.getTransactionType());
        existingStatement.setTag(tag); // aqui sim você seta o objeto inteiro

        return statementRepository.save(existingStatement);
    }

    public void updateAllForTagId(Tag tagToBeDeleted, Tag genericTag){
        List<Statement> statementsWithTag = statementRepository.findAllByTag(tagToBeDeleted);

        for (Statement s : statementsWithTag) {
            s.setTag(genericTag);
        }
        statementRepository.saveAll(statementsWithTag);
    }

    public void delete(Integer id){
        validateStatementExists(id);
        statementRepository.deleteById(id);
    }



    public List<Object> getAllFiltered(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer tagId,
            TransactionType type,
            String information) {


        if (startDate == null && endDate == null && tagId == null && type == null) {
            return Collections.singletonList(getAll());
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Statement> cq = cb.createQuery(Statement.class);
        Root<Statement> root = cq.from(Statement.class);

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("transactionDate"), startDate));

        if (endDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("transactionDate"), endDate));

        if (tagId != null)
            predicates.add(cb.equal(root.get("tag").get("id"), tagId));

        if (type != null)
            predicates.add(cb.equal(root.get("transactionType"), type));

        if (information != null)
            predicates.add(cb.equal(root.get("information"), information));

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return Collections.singletonList(entityManager.createQuery(cq).getResultList());
    }



    private Statement validateStatementExists(Integer id) {
        return statementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Statement by id [%s] not found".formatted(id)));
    }

}
