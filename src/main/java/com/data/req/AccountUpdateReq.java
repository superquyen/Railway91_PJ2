package com.data.req;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AccountUpdateReq {
    private int id;
    @NotBlank(message = "Không được để tên user trống")
    private String username;
    @NotBlank(message = "không được để trống password")
    private String password;
    @Email(message = "Email không đúng")
    private String email;
    @PastOrPresent
    private LocalDate createDate;
    @PastOrPresent
    private LocalDate updateDate;
    @Past
    private LocalDate birthDay;
    @Size(max = 100, message = "địa chỉ không được để dài quá 100 ký tự")
    private String address;
    private String role;
    private List<Integer>courseId;
}
