package com.iuh.kttkpm.be.registCourses.controllers;

import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.dto.RegistionRequest;
import com.iuh.kttkpm.be.registCourses.kafkas.producer.KafkaProducer;
import com.iuh.kttkpm.be.registCourses.models.Registion;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import com.iuh.kttkpm.be.registCourses.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/regist-course/registions")
@RequiredArgsConstructor
public class RegistionController {
    //    private final RegistionService registionService;
    private final RegistionRepository registionRepository;
    private final SectionClassRepository sectionClassRepository;
    private LookUpPointClient lookUpPointClient;
    private final KafkaProducer kafkaProducer;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> regist(@Valid @RequestBody RegistionRequest request) {
        try {
            Registion registion = new Registion();
            // Thiết lập thông tin đăng ký học phần từ yêu cầu của frontend
            registion.setStudent(lookUpPointClient.getStudentById(Long.valueOf(request.getStudentId())));
            registion.setSectionClass(sectionClassRepository.findById(Long.parseLong(request.getSectionClassId())).get());

            // Lưu thông tin đăng ký vào cơ sở dữ liệu
            registionRepository.save(registion);

            // Gửi thông tin đăng ký qua Kafka
            String message = String.format("{\"studentId\":\"%s\", \"sectionClassId\":\"%s\"}", request.getStudentId(), request.getSectionClassId());
            kafkaProducer.sendMessage(message);

            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .message("Registion request sent!")
                            .status(HttpStatus.OK.value())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .error("Failed to process registion request")
                            .status(HttpStatus.BAD_REQUEST.value())
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

