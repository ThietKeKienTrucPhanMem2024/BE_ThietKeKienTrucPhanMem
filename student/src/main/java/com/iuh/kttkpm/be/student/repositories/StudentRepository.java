package com.iuh.kttkpm.be.student.repositories;

import com.iuh.kttkpm.be.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}