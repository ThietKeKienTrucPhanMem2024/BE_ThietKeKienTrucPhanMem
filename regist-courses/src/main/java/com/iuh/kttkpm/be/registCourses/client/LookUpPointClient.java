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
    @GetMapping("/students/{studentId}")
    Student getStudentById(@PathVariable("studentId") Long studentId);
    @GetMapping("subjects")
    List<Subject> getAllSubjects();
    @GetMapping("/subjects/not-registered/{studentId}")
    List<Subject> getSubjectNotRegisteredByStudent(@PathVariable("studentId") Long studentId);
    @PostMapping("/registions/student-section-classes")
    void registerStudentToSubject(@PathVariable Long studentId, @PathVariable Long subjectId);
}
