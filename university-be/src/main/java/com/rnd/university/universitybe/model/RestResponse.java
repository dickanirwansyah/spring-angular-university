package com.rnd.university.universitybe.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {

    private static final String SUCCESS_MESSAGE = "success";

    private LocalDateTime timestamp;
    private String message;
    private Integer status;
    private Object data;

    public static RestResponse success(Object data){
        return RestResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(SUCCESS_MESSAGE)
                .data(data)
                .build();
    }
}
