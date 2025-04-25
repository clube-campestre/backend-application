package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.model.MedicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalDataRepository extends JpaRepository<MedicalData, Integer> {}
