package com.iuh.kttkpm.be.registCourses.kafkas.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.kttkpm.be.registCourses.client.LookUpPointClient;
import com.iuh.kttkpm.be.registCourses.dtos.RegistionRequest;
import com.iuh.kttkpm.be.registCourses.models.Registion;
import com.iuh.kttkpm.be.registCourses.repositories.RegistionRepository;
import com.iuh.kttkpm.be.registCourses.repositories.SectionClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final RegistionRepository registionRepository;
    private LookUpPointClient lookUpPointClient;
    private final SectionClassRepository sectionClassRepository;
    private final ObjectMapper objectMapper;


    @KafkaListener(topics = "registion_topic", groupId = "registCourses")
    public void consume(String message) {
        try {
            RegistionRequest request = objectMapper.readValue(message, RegistionRequest.class);
            Registion registion = new Registion();
            registion.setStudent(lookUpPointClient.getStudentById(Long.parseLong(request.getStudentId())));
            registion.setSectionClass(sectionClassRepository.findById(Long.parseLong(request.getSectionClassId())).get());
            registionRepository.save(registion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}