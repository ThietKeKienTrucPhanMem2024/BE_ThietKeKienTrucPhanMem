package com.iuh.kttkpm.be.registCourses.services;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.models.Subject;
import com.iuh.kttkpm.be.registCourses.repositories.StudentRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private LookUpPointClient lookUpPointClient;
    public List<Subject> findSubjectNotRegisteredByStudent(Long studentId){
        return lookUpPointClient.getSubjectNotRegisteredByStudent(studentId);
    }

}
