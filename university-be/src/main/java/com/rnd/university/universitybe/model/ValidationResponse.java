package com.rnd.university.universitybe.model;

import com.rnd.university.universitybe.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse extends BaseResponse {
    private boolean valid;
}
