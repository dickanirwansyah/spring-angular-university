package com.rnd.university.universitybe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_lesson_faculty")
public class LessonFaculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lesson_id", nullable = false)
    private Long lessonId;
    /** jadwal **/
    @Column(name = "time_table", nullable = false)
    private LocalDateTime timeTable;
    @Column(name = "total_student")
    private Integer totalStudent;
}
