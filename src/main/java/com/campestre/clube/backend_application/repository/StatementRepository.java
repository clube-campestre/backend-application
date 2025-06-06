package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Statement;
import com.campestre.clube.backend_application.entity.Tag;
import com.campestre.clube.backend_application.entity.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Integer> {

    @Query("""
                SELECT s FROM Statement s
                WHERE (:startDate IS NULL OR s.transactionDate >= :startDate)
                  AND (:endDate IS NULL OR s.transactionDate <= :endDate)
                  AND (:tagId IS NULL OR s.tag.id = :tagId)
                  AND (:type IS NULL OR s.transactionType = :type)
                  AND (:info IS NULL OR LOWER(s.information) LIKE LOWER(CONCAT('%', :info, '%')))
                ORDER BY s.transactionDate DESC
            """)
    Page<Statement> findByFilterAndPagination(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("tagId") Integer tagId,
            @Param("type") TransactionType type,
            @Param("info") String info,
            Pageable pageable
    );

    @Query("SELECT SUM(CASE WHEN s.transactionType = 'SAIDA' THEN -s.price ELSE s.price END) FROM Statement s")
    Double findAllPrices();

    boolean existsByInformationAndPriceAndTransactionDateAndTag(
            String information, Double price, LocalDateTime transactionDate, Tag tag);

    List<Statement> findAllByTag(Tag tag);
    void deleteByTag(Tag tag);

//    Object findByTagNameIgnoreCase(String barraca);
}
