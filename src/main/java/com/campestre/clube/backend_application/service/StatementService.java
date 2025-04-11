package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.StatementRepository;
import com.campestre.clube.backend_application.repository.TagRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private TagRepository tagRepository;

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

    private Statement validateStatementExists(Integer id) {
        return statementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Statement by id [%s] not found".formatted(id)));
    }

}
