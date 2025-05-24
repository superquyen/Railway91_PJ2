package com.data.controller;

import com.data.dto.CourseDTO;
import com.data.dto.LessonCourseDTO;
import com.data.entity.Course;
import com.data.entity.Lesson;

import com.data.req.CourseCreateReq;
import com.data.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public ResponseEntity<?> getAllCourse(Pageable pageable){
        Page<Course> courses = courseService.findAll(pageable);
        List<CourseDTO>courseDTOS = new ArrayList<>();
        courses.forEach(obj->{
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(obj.getId());
            courseDTO.setCourseName(obj.getCourseName());
            courseDTO.setSoGio(obj.getSoGio());
            courseDTO.setSoBuoi(obj.getSoBuoi());
            List<Lesson> lessons=obj.getLessons() != null ? obj.getLessons() : new ArrayList<>();
            List<LessonCourseDTO> lessonCourseDTOS = new ArrayList<>();
            lessons.forEach(obj2 -> {
                LessonCourseDTO lessonCourseDTO = new LessonCourseDTO();
                lessonCourseDTO.setId(obj2.getId());
                lessonCourseDTO.setLessonName(obj2.getLessonName());
                lessonCourseDTO.setMoTa(obj2.getMoTa());
                lessonCourseDTO.setSoGio(obj2.getSoGio());
                lessonCourseDTOS.add(lessonCourseDTO);
            });
            courseDTO.setLessons(lessonCourseDTOS);
            courseDTOS.add(courseDTO);
        });
        return ResponseEntity.ok(courseDTOS);
    }
    @PostMapping("createCourse")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseCreateReq courseCreateReq) {
        Optional<Course>courseOptional = courseService.findCourseByCourseName(courseCreateReq.getCourseName());
        if(courseOptional.isPresent()){
            return ResponseEntity.badRequest().body("Đã tồn tại khoá học này rồi");
        } else {
            Course course = new Course();
            course.setCourseName(courseCreateReq.getCourseName());
            course.setSoGio(courseCreateReq.getSoGio());
            course.setSoBuoi(courseCreateReq.getSoBuoi());
            courseService.save(course);
            return ResponseEntity.ok("Course created successfully");
        }
    }
    @PutMapping("updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseCreateReq courseCreateReq) {
        Optional<Course> courseOptional = courseService.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setCourseName(courseCreateReq.getCourseName());
            course.setSoGio(courseCreateReq.getSoGio());
            course.setSoBuoi(courseCreateReq.getSoBuoi());
            courseService.save(course);
            return ResponseEntity.ok("Course updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) {
        Optional<Course> courseOptional = courseService.findById(id);
        if (courseOptional.isPresent()) {
            courseService.delete(courseOptional.get());
            return ResponseEntity.ok("Course deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
