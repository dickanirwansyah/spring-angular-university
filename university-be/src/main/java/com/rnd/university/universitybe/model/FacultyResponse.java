package com.rnd.university.universitybe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnd.university.universitybe.base.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacultyResponse extends BaseResponse{

	@JsonProperty("faculty_id")
	private Long facultyId;
	
	@JsonProperty("faculty_name")
	private String facultyName;
	
	@JsonProperty("faculty_activated")
	private Integer facultyActivated;
}
