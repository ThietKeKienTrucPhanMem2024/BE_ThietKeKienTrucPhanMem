package com.iuh.kttkpm.be.registCourses.controllers;

import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regist-course/section-classes")
@RequiredArgsConstructor
public class SectionClassController {
    private final SectionClassRepository sectionClassRepository;
    private final SubjectRepository subjectRepository;
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> getAllSectionClassesBySubjectId(@RequestParam Long subjectId) {
        return ResponseEntity.ok(sectionClassRepository.findAllBySubject(subjectRepository.findById(subjectId).get()));
    }
}
