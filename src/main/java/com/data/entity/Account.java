package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private LocalDate createDate;
    private LocalDate updateDate;
    private LocalDate birthDay;
    private String address;
    private String role;
    @ManyToMany
    @JoinTable(
            name = "account_course",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private List<Course> courses;

}
