package com.data.service;

import com.data.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LessonService {


    Optional<Lesson> findByLessonName(String lessonName);

    void save(Lesson lesson);

    Optional<Lesson> findById(Integer id);

    void delete(Lesson lesson);

    Page<Lesson> findAll(Pageable pageable);
}
