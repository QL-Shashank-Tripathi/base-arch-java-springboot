package com.ql.springboottemplate.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserResponseDto {
    private String id;
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    private String lastname;
    @JsonIgnore
    @NotBlank
    private String password;


}
