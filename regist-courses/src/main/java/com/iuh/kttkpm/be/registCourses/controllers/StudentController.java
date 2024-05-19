package com.iuh.kttkpm.be.registCourses.controllers;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regist-course/students")
@RequiredArgsConstructor
public class StudentController {
    private LookUpPointClient lookUpPointClient;

    @GetMapping
    public ResponseEntity<Student> getAllStudentById(@RequestBody Long studentId){
        return ResponseEntity.ok(lookUpPointClient.getStudentById(studentId));
    }
}
