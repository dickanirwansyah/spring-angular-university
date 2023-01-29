package com.rnd.university.universitybe.repository;

import com.rnd.university.universitybe.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long>,
        JpaSpecificationExecutor<Faculty> {
}
