package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.StatementRepository;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private TagService tagService;

    public Statement register(Statement statement, Integer idTag){
        Tag tag = tagService.getById(idTag);

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
        Optional<Statement> statement = statementRepository.findById(id);
        statementNotFoundValidation(statement, id);

        return statement.get();
    }

    public Statement update(Statement statement, Integer id){
        Tag tag = tagService.getById(statement.getId());

        Optional<Statement> updateStatement = statementRepository.findById(id);
        statementNotFoundValidation(updateStatement, id);

        updateStatement.get().setId(statement.getId());
        updateStatement.get().setInformation(statement.getInformation());
        updateStatement.get().setPrice(statement.getPrice());
        updateStatement.get().setTransactionDate(statement.getTransactionDate());
        updateStatement.get().setTransactionType(statement.getTransactionType());
        updateStatement.get().setTag(tag);

        return updateStatement.get();
    }

    public void delete(Integer id){
        if(!statementRepository.existsById(id))
            throw new NotFoundException("Statement by id [%s] not found".formatted(id));

        statementRepository.deleteById(id);
    }

    private void statementNotFoundValidation(Optional<Statement> statement, Integer id){
        if(statement.isEmpty()){
            throw new NotFoundException("Statement by id [%s] not found".formatted(id));
        }
    }
}
