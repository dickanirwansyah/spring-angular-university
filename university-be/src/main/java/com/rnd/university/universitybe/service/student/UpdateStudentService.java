package com.rnd.university.universitybe.service.student;

import com.rnd.university.universitybe.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.StudentRequest;
import com.rnd.university.universitybe.model.StudentResponse;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateStudentService implements BaseService<StudentRequest, ValidationResponse>{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ValidationEmailService validationEmailService;
	
	@Autowired
	private ValidationPhoneNumberService validationPhoneNumberService;
	
	@Autowired
	private DetailStudentService detailStudentService;

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public ValidationResponse excute(StudentRequest request) {
		log.info("request update student={}",request.toString());
		
		StudentResponse studentResponse = this.detailStudentService.excute(GetIdRequest
				.builder()
				.id(request.getStudentId())
				.build());
		
		if (!request.getStudentPhoneNumber().equals(studentResponse.getStudentPhoneNumber())) {
			boolean validPhoneNumber = this.validationPhoneNumberService.excute(request).isValid();
			if (!validPhoneNumber) {
				log.info("phone number {} already exists ",request.getStudentPhoneNumber());
				throw new CustomErrorException("sorry phone number already exists");
			}
		}
		
		if (!request.getStudentEmail().equals(studentResponse.getStudentEmail())) {
			boolean validEmail = this.validationEmailService.excute(request).isValid();
			if (!validEmail) {
				log.info("email {} already exists ",request.getStudentEmail());
				throw new CustomErrorException("sorry email already exists");
			}
		}
		
		this.facultyRepository.findById(request.getStudentFaculty().getFacultyId())
				.ifPresentOrElse(data -> this.studentRepository.findById(request.getStudentId())
						.ifPresentOrElse(student -> {
							student.setName(request.getStudentName());
							student.setDateOfBirth(request.getStudentDateOfBirth());
							student.setBranch(request.getStudentBranch());
							student.setPhoneNumber(request.getStudentPhoneNumber());
							student.setEmail(request.getStudentEmail());
							student.setCurrentSemester(request.getStudentCurrentSemester());
							student.setFacultyId(data.getId());
							student.setProfileImage(request.getStudentProfileImage() == null ? student.getProfileImage() : request.getStudentProfileImage());
							this.studentRepository.save(student);
						}, ()-> {
							log.info("id student {} not found ",request.getStudentId());
							throw new CustomErrorException("sorry id student not found");
						}), ()-> {
					log.error("error because faculty id {} not found",request.getStudentFaculty().getFacultyId());
					throw new CustomErrorException("sorry faculty not found");
				});

		return ValidationResponse.builder()
				.valid(true)
				.build();
	}

}
