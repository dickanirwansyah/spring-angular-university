package com.rnd.university.universitybe.repository;

import com.rnd.university.universitybe.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select count(*) from mst_student where email=:email", nativeQuery = true)
    Optional<Integer> getStudentByEmail(@Param("email")String email);

    @Query(value = "select count(*) from mst_student where phone_number=:phoneNumber", nativeQuery = true)
    Optional<Integer> getStudentByPhoneNumber(@Param("phoneNumber")String phoneNumber);
}
