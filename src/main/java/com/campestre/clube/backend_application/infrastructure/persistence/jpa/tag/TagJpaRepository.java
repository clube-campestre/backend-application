package com.campestre.clube.backend_application.infrastructure.persistence.jpa.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Integer> {
    boolean existsBySurnameIgnoreCase(String surname);
    boolean existsBySurnameIgnoreCaseOrColorContains(String surname, String color);
    boolean existsBySurnameIgnoreCaseOrColorContainsAndIdNot(String surname, String color, Integer id);

    TagEntity findBySurnameIgnoreCase(String surname);
}
