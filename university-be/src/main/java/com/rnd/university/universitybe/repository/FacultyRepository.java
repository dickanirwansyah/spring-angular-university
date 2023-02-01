package com.rnd.university.universitybe.repository;

import com.rnd.university.universitybe.entity.Faculty;
import com.rnd.university.universitybe.projection.FacultyProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long>,
        JpaSpecificationExecutor<Faculty> {
	
	@Query(value = "select * from mst_faculty", nativeQuery = true)
	Page<FacultyProjection> getFacultyPage(Pageable pageable);
	
}


