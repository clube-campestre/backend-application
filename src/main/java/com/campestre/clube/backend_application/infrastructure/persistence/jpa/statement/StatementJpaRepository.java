package com.campestre.clube.backend_application.infrastructure.persistence.jpa.statement;

import com.campestre.clube.backend_application.core.domain.enums.TransactionType;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface StatementJpaRepository extends JpaRepository<StatementEntity, Long> {
    boolean existsByTagSurname(@Param("tagSurname") String tagSurname);
    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, BigDecimal price, Instant transactionDate, TagEntity tag);

    @Query("""
                SELECT s FROM StatementEntity s
                WHERE (:startDate IS NULL OR s.transactionDate >= :startDate)
                  AND (:endDate IS NULL OR s.transactionDate <= :endDate)
                  AND (:tagId IS NULL OR s.tag.id = :tagId)
                  AND (:type IS NULL OR s.transactionType = :type)
                  AND (:info IS NULL OR LOWER(s.information) LIKE LOWER(CONCAT('%', :info, '%')))
                ORDER BY s.transactionDate DESC
            """)
    Page<StatementEntity> findByFilterAndPagination(
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate,
            @Param("tagId") Long tagId,
            @Param("type") TransactionType type,
            @Param("info") String info,
            Pageable pageable
    );

    @Query("SELECT SUM(CASE WHEN s.transactionType = 'SAIDA' THEN -s.price ELSE s.price END) FROM StatementEntity s")
    BigDecimal findAllPrices();

    @Query("SELECT SUM(CASE WHEN s.transactionType = 'SAIDA' THEN -s.price ELSE s.price END) FROM StatementEntity s WHERE s.tag.id = :tagId")
    BigDecimal findAllPricesByTagId(@Param("tagId") Long tagId);

    List<StatementEntity> findAllByTag(TagEntity tag);

    void deleteByTag(TagEntity tag);
}
