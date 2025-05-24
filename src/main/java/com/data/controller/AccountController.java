package com.data.controller;

import com.data.entity.Account;
import com.data.req.AccountCreateReq;
import com.data.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("createAccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateReq accountCreateReq){
        Optional<Account> optionalAccount = accountService.findByUsername(accountCreateReq.getUsername());
        if(optionalAccount.isPresent()){
            return ResponseEntity.badRequest().body("Tai khoan da ton tai");
        }
        Account account = new Account();
        account.setCreateDate(accountCreateReq.getCreateDate());
        account.setEmail(accountCreateReq.getEmail());
        account.setUsername(accountCreateReq.getUsername());
        account.setPassword(accountCreateReq.getPassword());
        account.setRole(accountCreateReq.getRole());
        account.setBirthDay(accountCreateReq.getBirthDay());
        account.setAddress(accountCreateReq.getAddress());
        account.setUpdateDate(accountCreateReq.getUpdateDate());
        accountService.save(account);
        return ResponseEntity.ok("Ban da tao moi user thanh cong");
    }
    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Integer id,
                                            @RequestBody AccountCreateReq accountUpdateReq) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setUsername(accountUpdateReq.getUsername());
            account1.setEmail(accountUpdateReq.getEmail());
            account1.setPassword(accountUpdateReq.getPassword());
            account1.setRole(accountUpdateReq.getRole());
            accountService.save(account1);
            return ResponseEntity.ok(account1);
        } else {
            return ResponseEntity.badRequest().body("Khong tim thay account");
        }
    }
}
