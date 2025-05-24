package com.data.service;

import com.data.entity.Lesson;
import com.data.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Optional<Lesson> findByLessonName(String lessonName) {
        return lessonRepository.findByLessonName(lessonName);
    }

    @Override
    public void save(Lesson lesson) {
        if (lesson != null) {
            lessonRepository.save(lesson);
        } else {
            throw new IllegalArgumentException("Lesson cannot be null");
        }
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void delete(Lesson lesson) {
        if (lesson != null) {
            lessonRepository.delete(lesson);
        } else {
            throw new IllegalArgumentException("Lesson cannot be null");
        }
    }

    @Override
    public Page<Lesson> findAll(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }
}
