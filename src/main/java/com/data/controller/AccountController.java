package com.data.controller;

import com.data.dto.AccountDTO;
import com.data.entity.Account;
import com.data.entity.Course;
import com.data.req.AccountCreateReq;
import com.data.req.AccountUpdateReq;
import com.data.service.AccountServiceImpl;
import com.data.service.CourseServiceImpl;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Validated
@RestController

public class AccountController {
    @Autowired
    private CourseServiceImpl courseService;
    private final AccountServiceImpl accountService;


    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/createAccount")
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
    public ResponseEntity<?> getAllAccounts(Pageable pageable) {
        Page<Account> accounts= accountService.findAll(pageable);
        List<AccountDTO>accountDTOS= new ArrayList<>();
        accounts.forEach(obj->{
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(obj.getId());
            accountDTO.setUsername(obj.getUsername());
            accountDTO.setAddress(obj.getAddress());
            accountDTO.setBirthDay(obj.getBirthDay());
            accountDTO.setEmail(obj.getEmail());
            accountDTO.setPassword(obj.getPassword());
            accountDTO.setRole(obj.getRole());
            accountDTO.setCreateDate(obj.getCreateDate());
            accountDTO.setUpdateDate(obj.getUpdateDate());
            List<Integer>courseIds = obj.getCourses().stream().map(Course::getId).toList();
            accountDTO.setCourseId(courseIds);
            accountDTOS.add(accountDTO);
        });

        return ResponseEntity.ok(accountDTOS);
    }
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<?> updateAccount(@Valid @PathVariable Integer id,
                                           @RequestBody AccountUpdateReq accountUpdateReq) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setUsername(accountUpdateReq.getUsername());
            account1.setEmail(accountUpdateReq.getEmail());
            account1.setPassword(accountUpdateReq.getPassword());
            account1.setRole(accountUpdateReq.getRole());
            if(accountUpdateReq.getCourseId()!=null && !accountUpdateReq.getCourseId().isEmpty()){
                List<Course>courses = courseService.findAllById(accountUpdateReq.getCourseId());
                account1.setCourses(courses);
            }
            accountService.save(account1);
            return ResponseEntity.ok("ban da update thanh cong account");
        } else {
            return ResponseEntity.badRequest().body("Khong tim thay account");
        }
    }
    @DeleteMapping("deleteAccount/{id}")
    public ResponseEntity<?>deleteAccount(@PathVariable int id){
        Optional<Account>account = accountService.findById(id);
        if(account.isEmpty()){
            return ResponseEntity.badRequest().body("Khong tim thay account co id nay: "+id);
        }
        Account account1 = account.get();
        account1.getCourses().clear();
        accountService.save(account1);
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("da xoa thanh cong account voi id: "+id);
    }
    @GetMapping("account_list")
    public String getAccounts(Model model){
        List<Account>accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        accounts.forEach(obj->{
            System.out.println(obj.getId());
            System.out.println(obj.getUsername());
        });
        return "account_list";
    }

}
