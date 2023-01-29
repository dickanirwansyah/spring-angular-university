package com.rnd.university.universitybe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    /** gabungan, jurusan, **/
    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_category", nullable = false)
    private LessonCategory lessonCategory;

    @Column(name = "lesson_semester", nullable = false)
    private Integer lessonSemester;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;
}
