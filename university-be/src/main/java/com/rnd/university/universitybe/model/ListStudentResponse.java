package com.rnd.university.universitybe.model;

import com.rnd.university.universitybe.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListStudentResponse extends BaseResponse {
    private List<StudentResponse> studentResponses;
}
