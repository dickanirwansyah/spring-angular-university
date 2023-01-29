package com.rnd.university.universitybe.model;

import java.util.List;

import com.rnd.university.universitybe.base.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListFacultyResponse extends BaseResponse{

	private List<FacultyResponse> listFaculty;
	
}
