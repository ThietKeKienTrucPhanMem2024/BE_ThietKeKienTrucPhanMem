package com.iuh.kttkpm.be.registCourses.services;

import com.iuh.kttkpm.be.registCourses.models.SectionClass;
import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionClassService {
    private final SectionClassRepository sectionClassRepository;
    private final SubjectRepository subjectRepository;
    public List<SectionClass> getAllSectionClassBySubjectId(Long subjectId){
        return sectionClassRepository.findAllBySubject(subjectRepository.findById(subjectId).get());
    }

}
