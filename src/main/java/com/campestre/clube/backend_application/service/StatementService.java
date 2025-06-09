package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.controller.dtos.requests.StatementRequestDto;
import com.campestre.clube.backend_application.entity.models.Pagination;
import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import com.campestre.clube.backend_application.exceptions.ConflictException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.StatementRepository;
import com.campestre.clube.backend_application.repository.TagRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.misc.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private TagRepository tagRepository;

    public Statement register(Statement statement, String tagName) {
        String tagNameFormatted = tagName.toUpperCase();
        Tag tag = tagRepository.findBySurnameIgnoreCase(tagNameFormatted).orElseThrow(() ->
                new NotFoundException("Não encontramos a tag solicitada.")
        );

        if (statementRepository.existsByInformationAndPriceAndTransactionDateAndTag(statement.getInformation(), statement.getPrice(), statement.getTransactionDate(), tag)) {
            throw new ConflictException("Já existe um lançamento com as mesmas informações, valor, data e tag.");
        }
        statement.setTag(tag);
        return statementRepository.save(statement);
    }

    public List<Statement> getAll() {
        return statementRepository.findAll();
    }

    public Statement getById(Integer id) {
        return findByIdOrThrow(id);
    }

    public Statement update(StatementRequestDto dto, Integer id) {
        Statement existingStatement = findByIdOrThrow(id);
        String tagName = dto.getTagName().toUpperCase();

        Tag tag = tagRepository.findBySurnameIgnoreCase(tagName).orElseThrow(() ->
                new NotFoundException("Não encontramos a tag com o nome informado.")
        );

        existingStatement.setInformation(dto.getInformation());
        existingStatement.setPrice(dto.getPrice());
        existingStatement.setTransactionDate(dto.getTransactionDate());
        existingStatement.setTransactionType(dto.getTransactionType());
        existingStatement.setTag(tag);

        return statementRepository.save(existingStatement);
    }

    public void updateAllForTagId(Tag tagToBeDeleted, Tag genericTag) {
        List<Statement> statementsWithTag = statementRepository.findAllByTag(tagToBeDeleted);

        for (Statement s : statementsWithTag) {
            s.setTag(genericTag);
        }
        statementRepository.saveAll(statementsWithTag);
    }

    public void deleteByTag(String tagName) {
        String tagNameFormatted = tagName.toUpperCase();
        Tag tag = tagRepository.findBySurnameIgnoreCase(tagNameFormatted).orElseThrow(() ->
                new NotFoundException("Não foi possível encontrar a tag informada.")
        );
        statementRepository.deleteByTag(tag);
    }

    public void delete(Integer id) {
        existsByIdOrThrow(id);
        statementRepository.deleteById(id);
    }

    public Triple<List<Statement>, Pagination, Double> getByFilterAndPagination(
            LocalDateTime startDate, LocalDateTime endDate, Integer tagId, TransactionType type, String information,
            Integer page, Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Statement> result = statementRepository
                .findByFilterAndPagination(startDate, endDate, tagId, type, information, pageable);
        Double totalPrice = statementRepository.findAllPrices();
        return new Triple<>(result.getContent(), new Pagination(
                result.getNumber(), result.getSize(), result.getTotalElements(), result.getTotalPages()
        ), totalPrice);
    }

    public Pair<Double, Tag> getGoalByTagId(Integer id) {
        return new Pair<>(statementRepository.findAllPricesByTagId(id), tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não encontramos a tag solicitada."))
        );
    }

    public List<Statement> findAll() {
        return statementRepository.findAll();
    }



    private void existsByIdOrThrow(Integer id) {
        if (!statementRepository.existsById(id))
            throw new NotFoundException("Não encontramos o extrato solicitado.");
    }

    private Statement findByIdOrThrow(Integer id) {
        return statementRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Não encontramos o extrato solicitado.")
        );
    }
}
