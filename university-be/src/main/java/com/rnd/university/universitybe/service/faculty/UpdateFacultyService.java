package com.rnd.university.universitybe.service.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.FacultyRequest;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateFacultyService implements BaseService<FacultyRequest, ValidationResponse>{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public ValidationResponse excute(FacultyRequest request) {
		log.info("request update faculty={}",request.toString());
		facultyRepository.findById(request.getFacultyId())
			.ifPresentOrElse(data -> {
				data.setName(request.getFacultyName());
				data.setActive(request.getFacultyActivated());
				facultyRepository.save(data);
			}, ()-> {
				log.error("sorry id faculty {} not found ",request.getFacultyId());
				throw new CustomErrorException("sorry id faculty not found");
			});
		
		return ValidationResponse.builder()
				.valid(true)
				.build();
	}

}
