package com.iuh.kttkpm.be.registCourses.repositories;

import com.iuh.kttkpm.be.registCourses.models.Registion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistionRepository extends JpaRepository<Registion, Long> {
    List<Registion> findAllByStudentId(Long studentId);
}