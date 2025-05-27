package com.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private int id;
    private String courseName;
    private int soGio;
    private int soBuoi;
    private List<LessonCourseDTO>lessons;
    private List<AccountDTO>accountDTOS;
}
