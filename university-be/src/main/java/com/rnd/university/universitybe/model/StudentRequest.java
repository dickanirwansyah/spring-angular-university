package com.rnd.university.universitybe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnd.university.universitybe.base.BaseRequest;
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
public class StudentRequest extends BaseRequest {

	@JsonProperty("student_id")
	private Long studentId;
	
    @JsonProperty("student_name")
    private String studentName;

    @JsonProperty("student_email")
    private String studentEmail;

    /** SMA, SMK, MAN **/
    @JsonProperty("student_branch")
    private String studentBranch;

    @JsonProperty("student_phone_number")
    private String studentPhoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonProperty("student_date_of_birth")
    private Date studentDateOfBirth;
    
    @JsonProperty("student_profile_image")
    private String studentProfileImage;

    @JsonProperty("student_faculty")
    private FacultyRequest studentFaculty;
    
    @JsonProperty("student_current_semester")
    private Integer studentCurrentSemester;
}
