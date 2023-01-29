package com.rnd.university.universitybe.service.faculty;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.entity.Faculty;
import com.rnd.university.universitybe.model.FacultyRequest;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateFacultyService implements BaseService<FacultyRequest, ValidationResponse> {
    
	@Autowired
	private FacultyRepository facultyRepository;
	
    @Override
    public ValidationResponse excute(FacultyRequest request) {
        log.info("create faculty={}",request.toString());
        Faculty faculty = new Faculty();
        faculty.setName(request.getFacultyName());
        faculty.setActive(request.getFacultyActivated());
        facultyRepository.save(faculty);
        return ValidationResponse
        		.builder()
        		.valid(true)
        		.build();
    }
}
