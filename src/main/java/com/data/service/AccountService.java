package com.data.service;


import com.data.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface AccountService extends UserDetailsService {
    List<Account>getAll();
    Account findByEmail(String mail);

    Optional<Account> findByUsername(String username);

    void save(Account account);

    Optional<Account> findById(Integer id);
}
