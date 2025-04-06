package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    boolean existsBySurnameOrColorContains(String surname, String color);
}
