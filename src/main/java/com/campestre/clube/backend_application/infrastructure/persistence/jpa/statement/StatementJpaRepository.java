package com.campestre.clube.backend_application.infrastructure.persistence.jpa.statement;

import com.campestre.clube.backend_application.core.domain.Tag;
import com.campestre.clube.backend_application.core.domain.enums.TransactionType;
import com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface StatementJpaRepository extends JpaRepository<StatementEntity, Integer> {
    boolean existsByTagId(@Param("tagId") Integer tagId);
    boolean existsByTagSurname(@Param("tagSurname") String tagSurname);
    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, BigDecimal price, LocalDateTime transactionDate, TagEntity tag);

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
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("tagId") Integer tagId,
            @Param("type") TransactionType type,
            @Param("info") String info,
            Pageable pageable
    );

    @Query("SELECT SUM(CASE WHEN s.transactionType = 'SAIDA' THEN -s.price ELSE s.price END) FROM StatementEntity s")
    BigDecimal findAllPrices();

    @Query("SELECT SUM(CASE WHEN s.transactionType = 'SAIDA' THEN -s.price ELSE s.price END) FROM StatementEntity s WHERE s.tag.id = :tagId")
    BigDecimal findAllPricesByTagId(@Param("tagId") Integer tagId);

    List<StatementEntity> findAllByTag(Tag tag);

    void deleteByTag(Tag tag);

    String tag(TagEntity tag);
}
