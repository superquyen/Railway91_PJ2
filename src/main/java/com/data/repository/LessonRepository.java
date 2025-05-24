package com.data.repository;

import com.data.entity.Lesson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findByLessonName(String lessonName);

}
