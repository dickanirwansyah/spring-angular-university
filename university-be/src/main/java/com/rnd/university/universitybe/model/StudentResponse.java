package com.rnd.university.universitybe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnd.university.universitybe.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse extends BaseResponse {

    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("student_phone_number")
    private String studentPhoneNumber;

    @JsonProperty("student_branch")
    private String studentBranch;

    @JsonProperty("student_email")
    private String studentEmail;

    @JsonProperty("student_faculty")
    private FacultyResponse studentFaculty;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("student_date_of_birth")
    private Date studentDateOfBirth;
}
