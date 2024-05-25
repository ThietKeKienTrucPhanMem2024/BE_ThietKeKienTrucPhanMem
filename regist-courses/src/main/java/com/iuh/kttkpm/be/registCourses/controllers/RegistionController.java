package com.iuh.kttkpm.be.registCourses.controllers;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.dtos.RegistionRequest;
import com.iuh.kttkpm.be.registCourses.kafkas.producer.KafkaProducer;
import com.iuh.kttkpm.be.registCourses.models.Registion;
import com.iuh.kttkpm.be.registCourses.models.SectionClass;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.registCourses.repositories.StudentRepository;
import com.iuh.kttkpm.be.registCourses.responses.ApiResponse;
import com.iuh.kttkpm.be.registCourses.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/regist-courses/registions")
@RequiredArgsConstructor
public class RegistionController {
    //    private final RegistionService registionService;
    private final RegistionRepository registionRepository;
    private final SectionClassRepository sectionClassRepository;
    private final LookUpPointClient lookUpPointClient;
    private final KafkaProducer kafkaProducer;
    private final StudentRepository studentRepository;
    private final SubjectService subjectService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> regist(@Valid  @RequestBody RegistionRequest registionRequest) {
        try {
            SectionClass sectionClass = sectionClassRepository.findById(Long.valueOf(registionRequest.getSectionClassId())).get();
            if(sectionClass.getStudentNumber() > 90 ){
                return ResponseEntity.badRequest().body(
                        ApiResponse.builder()
                                .error("Section class is full")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
            }
            if(subjectService.checkPrerequisiteSubject(Long.valueOf(registionRequest.getStudentId()), sectionClass.getSubject().getPrerequisites().stream().map(p -> p.getSubjectId()).toList())){
                return ResponseEntity.badRequest().body(
                        ApiResponse.builder()
                                .error("Student has not completed the prerequisites")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
            }
            kafkaProducer.sendEnrollmentMessage(registionRequest.getStudentId(),registionRequest.getSectionClassId());

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Registion request sent!")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .error("Failed to process registion request")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteRegistion(@PathVariable("id") Long id) {
        try {
            kafkaProducer.sendCancelEnrollmentMessage(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Delete registion success")
                            .status(200)
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .error(e.getMessage())
                            .status(400)
                            .success(false)
                            .build()
            );
        }
    }

    @PostMapping("/student-section-classes")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudentSectionClass(@RequestBody Long studentId, @RequestBody Long sectionClassId){
        lookUpPointClient.registerStudentToSubject(studentId, sectionClassId);
    }
    @GetMapping("/students/{studentId}")
    public ResponseEntity<?> getAllRegistionByStudentId(@PathVariable("studentId") Long studentId){
        return ResponseEntity.ok(registionRepository.findAllByStudent(studentRepository.findById(studentId).get()));
    }
}

