package com.iuh.kttkpm.be.registCourses.services;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.models.Subject;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import com.iuh.kttkpm.be.registCourses.repositories.StudentRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private LookUpPointClient lookUpPointClient;
    private final RegistionRepository registionRepository;
    public List<Subject> findSubjectNotRegisteredByStudent(Long studentId){
        return lookUpPointClient.getSubjectNotRegisteredByStudent(studentId);
    }

    public List<Subject> findSubjectNotRegisteredByStudentAndListSubject(Long studentId, List<Subject> subjects){
        Set<Subject> registeredSubject = registionRepository.findAllByStudent(studentRepository.findById(studentId).get()).stream()
                .map(registion -> registion.getSectionClass().getSubject())
                .collect(java.util.stream.Collectors.toSet());
        subjects.removeAll(registeredSubject);
        return subjects;
    }
}
