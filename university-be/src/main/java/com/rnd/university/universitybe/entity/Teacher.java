package com.rnd.university.universitybe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "activated", nullable = false)
    private Integer activated;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
    
    @Column(name = "faculty_id")
    private Long facultyId;
}
