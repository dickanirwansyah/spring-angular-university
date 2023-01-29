package com.rnd.university.universitybe.service.faculty;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.model.EmptyRequest;
import com.rnd.university.universitybe.model.FacultyResponse;
import com.rnd.university.universitybe.model.ListFacultyResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ListFacultyService implements BaseService<EmptyRequest, ListFacultyResponse>{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public ListFacultyResponse excute(EmptyRequest request) {
		log.info("list category");
		return ListFacultyResponse.builder()
				.listFaculty(facultyRepository.findAll().stream()
						.map(data -> FacultyResponse.builder()
								.facultyId(data.getId())
								.facultyActivated(data.getActive())
								.facultyName(data.getName())
								.build())
						.collect(Collectors.toList()))
				.build();
	}

}
