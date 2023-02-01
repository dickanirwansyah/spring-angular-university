package com.rnd.university.universitybe.service.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.FacultyResponse;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetailFacultyService implements BaseService<GetIdRequest, FacultyResponse>{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	
	@Override
	public FacultyResponse excute(GetIdRequest request) {
		log.info("request get faculty id = {} ",request.getId());
		return facultyRepository.findById(request.getId())
				.map(data -> FacultyResponse
						.builder()
						.facultyId(data.getId())
						.facultyActivated(data.getActive())
						.facultyName(data.getName())
						.build())
				.orElseThrow(() -> 
				new CustomErrorException("sorry faculty id not found"));
	}

}
