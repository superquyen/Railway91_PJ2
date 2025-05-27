package com.data.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AccountDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate birthDay;
    private String address;
    private String role;
    private List<Integer> courseId;
    public AccountDTO(){

    }
}
