package com.iuh.kttkpm.be.lookUpPoints.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SectionClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long sectionClassId;
    private String name;
    private String semester;
    private String year;
    private int studentNumber;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    private String startDate;
    private String endDate;
    private List<DayOfWeek> theorySchedule;
    private List<DayOfWeek> practiceSchedule;
    private String room;
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;
    @OneToMany
    @JoinColumn(name = "section_class_id")
    private List<StudentSectionClass> studentSectionClasses;
    @OneToMany(mappedBy = "sectionClass")
    private List<Scores> scores;

}
