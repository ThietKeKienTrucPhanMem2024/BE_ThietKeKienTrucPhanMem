package com.iuh.kttkpm.be.registCourses.client;

import com.iuh.kttkpm.be.registCourses.models.Student;
import com.iuh.kttkpm.be.registCourses.models.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.look-up-points-url}")
public interface LookUpPointClient {
    @GetMapping("/api/v1/register-courses/students/{id}")
    Student getStudentById(@PathVariable Long id);
    @GetMapping("/api/v1/register-courses/subjects")
    List<Subject> getAllSubjects();
    @GetMapping("/api/v1/register-courses/subjects/not-registered/{studentId}")
    List<Subject> getSubjectNotRegisteredByStudent(@PathVariable Long id);
    @PostMapping("/api/v1/register-courses/registions/student-section-classes")
    void registerStudentToSubject(@PathVariable Long studentId, @PathVariable Long subjectId);
}
