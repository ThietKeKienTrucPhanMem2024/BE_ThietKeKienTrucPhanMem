package com.iuh.kttkpm.be.lookUpPoints.controllers;

import com.iuh.kttkpm.be.lookUpPoints.models.StudentSectionClass;
import com.iuh.kttkpm.be.lookUpPoints.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.lookUpPoints.repositories.StudentRepository;
import com.iuh.kttkpm.be.lookUpPoints.repositories.StudentSectionClassRepository;
import com.iuh.kttkpm.be.lookUpPoints.services.StudentSectionClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/look-up-points/student-section-classes")
@RequiredArgsConstructor
public class StudentSectionClassController {
    private final StudentSectionClassService studentSectionClassService;
    private final StudentSectionClassRepository studentSectionClassRepository;
    private final StudentRepository studentRepository;
    private final SectionClassRepository sectionClassRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudentSectionClass(@RequestBody Long studentId, @RequestBody Long sectionClassId){
        StudentSectionClass studentSectionClass = new StudentSectionClass();
        studentSectionClass.setStudent(studentRepository.findById(studentId).get());
        studentSectionClass.setSectionClass(sectionClassRepository.findById(sectionClassId).get());
        studentSectionClassRepository.save(studentSectionClass);
    }
}
