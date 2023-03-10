package com.rnd.university.universitybe.service.student;

import com.rnd.university.universitybe.base.BaseService;
import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.FacultyResponse;
import com.rnd.university.universitybe.model.GetIdRequest;
import com.rnd.university.universitybe.model.StudentResponse;
import com.rnd.university.universitybe.repository.FacultyRepository;
import com.rnd.university.universitybe.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DetailStudentService implements BaseService<GetIdRequest, StudentResponse> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public StudentResponse excute(GetIdRequest request) {
        log.info("student get id="+request.getId());
        return studentRepository.findById(request.getId())
                .map(response -> StudentResponse.builder()
                        .studentId(response.getId())
                        .studentEmail(response.getEmail())
                        .studentName(response.getName())
                        .studentBranch(response.getBranch())
                        .studentDateOfBirth(response.getDateOfBirth())
                        .studentPhoneNumber(response.getPhoneNumber())
                        .studentCurrentSemester(response.getCurrentSemester())
                        .studentFaculty(this.facultyRepository.findById(response.getFacultyId())
                                .map(data -> FacultyResponse.builder()
                                        .facultyId(data.getId())
                                        .facultyName(data.getName())
                                        .facultyActivated(data.getActive())
                                        .build())
                                .orElseThrow(() -> new CustomErrorException("sorry faculty id not found")))
                        .build())
                .orElseThrow(()-> new CustomErrorException("data with id"+request.getId()+" not found"));
    }
}
