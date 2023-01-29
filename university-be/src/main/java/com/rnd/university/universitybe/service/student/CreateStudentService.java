package com.rnd.university.universitybe.service.student;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.entity.Student;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.StudentRequest;
import com.rnd.university.universitybe.model.StudentResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;
import com.rnd.university.universitybe.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateStudentService implements BaseService<StudentRequest, StudentResponse> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ValidationEmailService validationEmailService;

    @Autowired
    private ValidationPhoneNumberService validationPhoneNumberService;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public StudentResponse excute(StudentRequest request) {
        log.info("create user={}", request.toString());

        boolean validEmail = validationEmailService.excute(request).isValid();
        boolean validPhonumber = validationPhoneNumberService.excute(request).isValid();

        if (!validPhonumber){
            throw new CustomErrorException("sorry phone number is already exist");
        }
        if (!validEmail){
            throw new CustomErrorException("sorry email is already exists");
        }

        this.facultyRepository.findById(request.getStudentFaculty().getFacultyId())
                .ifPresentOrElse(faculty -> {
                            studentRepository.save(Student.builder()
                            .name(request.getStudentName())
                            .branch(request.getStudentBranch())
                            .dateOfBirth(request.getStudentDateOfBirth())
                            .email(request.getStudentEmail())
                            .phoneNumber(request.getStudentPhoneNumber())
                            .facultyId(request.getStudentFaculty().getFacultyId())
                            .currentSemester(request.getStudentCurrentSemester())
                            .profileImage(request.getStudentProfileImage())
                            .build());
                    log.info("finish create user..");
                }, ()-> {
                    log.error("error because faculty id {} not found",request.getStudentFaculty().getFacultyId());
                    throw new CustomErrorException("error faculty id not found");
                });

        return StudentResponse.builder()
                .studentId(0l)
                .studentName(request.getStudentName())
                .build();
    }
}
