package com.iuh.kttkpm.be.registCourses.controllers;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.models.Registion;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.registCourses.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regist-course/registions")
@RequiredArgsConstructor
public class RegistionController {
    //    private final RegistionService registionService;
    private final RegistionRepository registionRepository;
    private final SectionClassRepository sectionClassRepository;
    private LookUpPointClient lookUpPointClient;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<?>> createRegistion(@RequestBody Long studentId, @RequestBody Long sectionClassId) {
        try {
            Registion registion = new Registion();
            registion.setStudent(lookUpPointClient.getStudentById(studentId));
            registion.setSectionClass(sectionClassRepository.findById(sectionClassId).get());
            registionRepository.save(registion);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Create registion success")
                            .status(200)
                            .success(true)
                            .build()
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .error(e.getMessage())
                            .status(400)
                            .success(false)
                            .build()
            );
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteRegistion(@PathVariable Long id) {
        try {
            registionRepository.deleteById(id);
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Delete registion success")
                            .status(200)
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .error(e.getMessage())
                            .status(400)
                            .success(false)
                            .build()
            );
        }
    }

    @PostMapping("/student-section-classes")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudentSectionClass(@RequestBody Long studentId, @RequestBody Long sectionClassId){
        lookUpPointClient.registerStudentToSubject(studentId, sectionClassId);
    }
}
