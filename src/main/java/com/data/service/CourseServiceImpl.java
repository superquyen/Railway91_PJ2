package com.data.service;

import com.data.entity.Course;
import com.data.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Optional<Course> findCourseByCourseName(String courseName) {
        return courseRepository.findCourseByCourseName(courseName);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(Course course) {
        if (course != null) {
            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("Course cannot be null");
        }
    }

    @Override
    public void delete(Course course) {
        if (course != null) {
            courseRepository.delete(course);
        } else {
            throw new IllegalArgumentException("Course cannot be null");
        }
    }

    @Override
    public List<Course> findAllById(List<Integer> courseId) {
        return courseRepository.findAllById(courseId);
    }

}
