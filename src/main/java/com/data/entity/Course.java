package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private int soBuoi;
    private int soGio;
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Lesson>lessons;
    @ManyToMany
    @JoinTable(name = "Account_Course",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account>accounts;
    @ManyToOne
    @JoinColumn(name = "certificate_id")
    @ToString.Exclude
    private Certificate certificate;
}
