package com.data.repository;

import com.data.entity.Certificate;
import com.data.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findAll(Pageable pageable);

    Optional<Course> findCourseByCourseName(String courseName);
    @Query("SELECT c.certificate FROM Course c WHERE c.id = :courseId")
    Certificate findCertificateByCourseId(@Param("courseId") int courseId);

}
