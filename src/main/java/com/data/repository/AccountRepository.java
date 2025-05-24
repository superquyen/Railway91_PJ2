package com.data.repository;

import com.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByEmail(String email);

    Optional<Account> findByUsername(String username);
}
