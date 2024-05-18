package com.iuh.kttkpm.be.registCourses.repositories;

import com.iuh.kttkpm.be.registCourses.models.SectionClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionClassRepository extends JpaRepository<SectionClass, Long> {
    List<SectionClass> findAllBySubjectId(Long subjectId);
}