package com.iuh.kttkpm.be.student.services;

import com.iuh.kttkpm.be.student.models.Student;
import com.iuh.kttkpm.be.student.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

//    fileAll
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
}
