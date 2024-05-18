package com.iuh.kttkpm.be.registCourses.services;

import com.iuh.kttkpm.be.registCourses.models.Registion;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistionService {
    private final RegistionRepository registionRepository;
    public List<Registion> getAllRegistionByStudentId(Long studentId){
        return registionRepository.findAllByStudentId(studentId);
    }
}
