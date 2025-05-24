package com.data.service;

import com.data.entity.Account;
import com.data.repository.AccountRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.Optional;

@Service
@ControllerAdvice
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
       this.accountRepository = accountRepository;
    }
    @Override
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @Override
    public Account findByEmail(String mail) {
        return accountRepository.findByEmail(mail);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);
        if(account == null){
            throw new UsernameNotFoundException("account khong dung");
        }
        return new User(email, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole()));
    }

}
