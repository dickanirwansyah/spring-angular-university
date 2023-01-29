package com.rnd.university.universitybe.service.student;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.EmptyRequest;
import com.rnd.university.universitybe.model.FacultyResponse;
import com.rnd.university.universitybe.model.ListStudentResponse;
import com.rnd.university.universitybe.model.StudentResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;
import com.rnd.university.universitybe.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ListStudentService implements BaseService<EmptyRequest, ListStudentResponse> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public ListStudentResponse excute(EmptyRequest request) {
        log.info("list student..");
        return ListStudentResponse.builder()
                .studentResponses(studentRepository.findAll().stream()
                        .map(response -> StudentResponse.builder()
                                .studentId(response.getId())
                                .studentName(response.getName())
                                .studentPhoneNumber(response.getPhoneNumber())
                                .studentDateOfBirth(response.getDateOfBirth())
                                .studentBranch(response.getBranch())
                                .studentEmail(response.getEmail())
                                .studentFaculty(this.facultyRepository.findById(response.getFacultyId())
                                        .map(data -> FacultyResponse
                                                .builder()
                                                .facultyId(data.getId())
                                                .facultyName(data.getName())
                                                .facultyActivated(data.getActive())
                                                .build())
                                        .orElseThrow(() -> new CustomErrorException("sorry faculty not found")))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
