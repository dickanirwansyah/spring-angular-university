package com.rnd.university.universitybe.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeleteStudentService implements BaseService<GetIdRequest, ValidationResponse>{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public ValidationResponse excute(GetIdRequest request) {
		log.info("request delete student by id ={} ",request.getId());
		this.studentRepository.findById(request.getId())
			.ifPresentOrElse(data -> {
				this.studentRepository.delete(data);
			}, ()-> {
				log.error("student with id {} not found ",request.getId());
				throw new CustomErrorException("sorry student id not found");
			});
		return ValidationResponse.builder()
				.valid(true)
				.build();
	}

}
