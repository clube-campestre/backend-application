package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatementRepository
        extends JpaRepository<Statement, Integer>,
        JpaSpecificationExecutor<Statement> {

    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, Double price, LocalDateTime transactionDate, Tag tag);

    List<Statement> findAllByTag(Tag tag);
}
