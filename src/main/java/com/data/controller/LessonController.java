package com.data.controller;

import com.data.dto.LessonDTO;
import com.data.entity.Course;
import com.data.entity.Lesson;
import com.data.repository.CourseRepository;
import com.data.req.LessonCreateReq;
import com.data.req.LessonUpdateReq;
import com.data.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LessonController {
    @Autowired
    private CourseRepository courseRepository;

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping("lessons")
    public ResponseEntity<?> getAllLessons(Pageable pageable) {
        Page<Lesson> lessons= lessonService.findAll(pageable);
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        lessons.forEach(obj -> {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setId(obj.getId());
            lessonDTO.setLessonName(obj.getLessonName());
            lessonDTO.setMoTa(obj.getMoTa());
            lessonDTO.setSoGio(obj.getSoGio());
            lessonDTO.setCourseId(obj.getCourse().getId());
            lessonDTO.setCourseName(obj.getCourse().getCourseName());
            lessonDTOS.add(lessonDTO);
        });
        return ResponseEntity.ok(lessonDTOS);
    }
    @PostMapping("createLesson")
    public ResponseEntity<?> createLesson(@Valid LessonCreateReq lessonCreateReq) {
        Optional<Lesson> lessonOptional = lessonService.findByLessonName(lessonCreateReq.getLessonName());
        if(lessonOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Lesson already exists");
        }
        Optional<Course> courseOptional = courseRepository.findById(lessonCreateReq.getCourseId());
        if(!courseOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Ban chua gan dung lesson vao khoa hoc");
        }
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonCreateReq.getLessonName());
        lesson.setMoTa(lessonCreateReq.getMoTa());
        lesson.setSoGio(lessonCreateReq.getSoGio());
        lesson.setCourse(courseOptional.get());
        lessonService.save(lesson);
        return ResponseEntity.ok("Create lesson successfully");
    }
    @PutMapping("updateLesson/{id}")
    public ResponseEntity<?>updateLesson(@Valid @PathVariable Integer id,
                                         @RequestBody LessonUpdateReq lessonUpdateReq){
        Lesson lesson = lessonService.findById(id).orElse(null);
        if(lesson== null) {
            return ResponseEntity.badRequest().body("Lesson not found");
        }
        Optional<Course>courseOptional = courseRepository.findById(lessonUpdateReq.getCourseId());
        if(courseOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("khong tim thay course ban muon cap nhat");
        }
        lesson.setLessonName(lessonUpdateReq.getLessonName());
        lesson.setMoTa(lessonUpdateReq.getMoTa());
        lesson.setSoGio(lessonUpdateReq.getSoGio());
        lesson.setCourse(courseOptional.get());
        lessonService.save(lesson);
        return ResponseEntity.ok("cap nhat mon hoc thanh cong");
    }
    @DeleteMapping("deleteLesson/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Integer id) {
        Lesson lesson = lessonService.findById(id).orElse(null);
        if(lesson == null) {
            return ResponseEntity.badRequest().body("khong ton tai mon hoc co id la: "+ id );
        }
        lessonService.delete(lesson);
        return ResponseEntity.ok("Ban da xoa thanh cong mon hoc co id: "+ id);
    }
}
