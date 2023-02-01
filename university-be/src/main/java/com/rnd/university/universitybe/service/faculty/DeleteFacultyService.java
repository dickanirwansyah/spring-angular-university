package com.rnd.university.universitybe.service.faculty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.entity.Student;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;
import com.rnd.university.universitybe.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeleteFacultyService implements BaseService<GetIdRequest, ValidationResponse>{
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public ValidationResponse excute(GetIdRequest request) {
		log.info("request delete faculty by id");
		this.facultyRepository.findById(request.getId())
			.ifPresentOrElse(response -> {
				
				List<Student> checkStudentFacultys = studentRepository.findAll().stream()
						.filter(data -> data.getFacultyId().equals(response.getId()))
						.collect(Collectors.toList());
				
				if (!checkStudentFacultys.isEmpty() || checkStudentFacultys.size() > 0) {
					throw new CustomErrorException("sorry you cannot delete this faculty");
				}
				
				facultyRepository.delete(response);
				
			}, ()-> {
				log.error("sorry faculty with id {} not found ",request.getId());
				throw new CustomErrorException("sorry faculty not found");
			});
		return ValidationResponse.builder()
				.valid(true)
				.build();
	}

}
