package com.iuh.kttkpm.be.student.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String email;
//    private String phone;
//    private String address;
//    private String avatar;
//    private String username;
//    private String password;
//    private String role;
//    private boolean status;
    private String schoolId;

}
