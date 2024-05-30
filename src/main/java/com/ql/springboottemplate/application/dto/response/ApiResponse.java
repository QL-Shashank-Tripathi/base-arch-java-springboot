package com.ql.springboottemplate.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

}
