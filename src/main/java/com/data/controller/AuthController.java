package com.data.controller;

import com.data.dto.LoginInfoDTO;
import com.data.entity.Account;
import com.data.service.AccountServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountServiceImpl accountService;
    @GetMapping("/login")
    public LoginInfoDTO login(Principal principal){
        String username = principal.getName();
        Account entity = accountService.getAccountByUsername(username);
        return modelMapper.map(entity, LoginInfoDTO.class);
    }
}
