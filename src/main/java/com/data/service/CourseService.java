package com.data.service;

import com.data.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CourseService {
    Page<Course> findAll(Pageable pageable);

    Optional<Course> findCourseByCourseName(String courseName);


    Optional<Course> findById(Integer id);

    void save(Course course);

    void delete(Course course);
}
