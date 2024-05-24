package com.iuh.kttkpm.be.registCourses.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Column
    private String theorySchedule;

    @Column
    private String practiceSchedule;
    private String room;

    public void setTheorySchedule(List<WeekDay> days) {
        this.theorySchedule = days.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    public List<WeekDay> getTheorySchedule() {
        if (this.theorySchedule == null || this.theorySchedule.isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.stream(this.theorySchedule.split(",")).map(WeekDay::valueOf).collect(Collectors.toList());
    }

    public void setPracticeSchedule(List<WeekDay> days) {
        this.practiceSchedule = days.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    public List<WeekDay> getPracticeSchedule() {
        if (this.practiceSchedule == null || this.practiceSchedule.isEmpty()) {
            return Arrays.asList();
        }
        return Arrays.stream(this.practiceSchedule.split(",")).map(WeekDay::valueOf).collect(Collectors.toList());
    }

}
