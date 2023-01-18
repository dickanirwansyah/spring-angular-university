package com.rnd.university.universitybe.service;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.model.StudentRequest;
import com.rnd.university.universitybe.model.ValidationResponse;
import com.rnd.university.universitybe.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ValidationPhoneNumberService implements BaseService<StudentRequest, ValidationResponse> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ValidationResponse excute(StudentRequest request) {
        if (studentRepository.getStudentByPhoneNumber(request.getStudentPhoneNumber()).get() > 0){
            log.info("validation phone -> phone number is already exists");
            return ValidationResponse.builder().valid(false).build();
        }else{
            log.info("validation phone -> phone number is not found");
            return ValidationResponse.builder().valid(true).build();
        }
    }
}
