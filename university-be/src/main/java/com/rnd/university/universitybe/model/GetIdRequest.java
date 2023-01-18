package com.rnd.university.universitybe.model;

import com.rnd.university.universitybe.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetIdRequest extends BaseRequest {
    private Long id;
}
