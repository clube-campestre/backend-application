package com.campestre.clube.backend_application.repository;

import com.campestre.clube.backend_application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Boolean existsByEmailAndIdNot(String email, Integer id);
    Boolean existsByEmailAndPassword(String email, String password);
    Boolean existsByEmailOrCpf(String email, String cpf);
    Account findByEmailAndPassword(String email, String password);
    boolean existsByCpfAndIdNot(String cpf, Integer id);
}
