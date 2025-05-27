package com.data.controller;

import com.data.dto.CertificateDTO;
import com.data.entity.Certificate;
import com.data.entity.Course;
import com.data.repository.CourseRepository;
import com.data.service.CertificateService;
import com.data.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Validated
public class CertificateController {
    @Autowired
    private CourseRepository courseRepository;

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }
    @GetMapping("certificate")
    public ResponseEntity<?> getCertificate() {
        List<Certificate> certificates = certificateService.findAll();
        List<CertificateDTO> dtos = certificates.stream()
                .map(CertificateDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("certificate/{courseId}")
    public ResponseEntity<?> getCertificateByCourseId(@PathVariable int courseId){
//        Course course = courseService.findById(courseId).orElse(null);
//        if (course == null || course.getCertificate()==null){
//            return ResponseEntity.badRequest().body("khong tim thay course trong certificate nay");
//        }
//        CertificateDTO certificateDTO = new CertificateDTO(course.getCertificate());
//        return ResponseEntity.ok(certificateDTO);
        Certificate certificate = courseRepository.findCertificateByCourseId(courseId);
        if(certificate == null){
            return ResponseEntity.badRequest().body("khong co courseId nay trong certificate nay");
        }
        CertificateDTO certificateDTO = new CertificateDTO(certificate);
        return ResponseEntity.ok(certificateDTO);
    }
}
