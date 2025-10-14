package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {
    boolean existsBySurnameIgnoreCase(String surname);
    boolean existsByColor(String color);
    boolean existsBySurnameIgnoreCaseAndIdNot(String surname, Long id);
    boolean existsByColorAndIdNot(String color, Long id);

    TagEntity findBySurnameIgnoreCase(String surname);
}
