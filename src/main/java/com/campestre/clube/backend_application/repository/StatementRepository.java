package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface StatementRepository extends JpaRepository<Statement, Integer> {
    boolean existsByInformationAndPriceAndTransactionDateAndTag(String information, Double price, LocalDateTime transactionDate, Tag tag);
}
