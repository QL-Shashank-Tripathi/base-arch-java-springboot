package com.ql.springboottemplate.application.dto.request;

import com.ql.springboottemplate.application.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserDto {

    @NotBlank
    @Email( message = "Please Enter a valid Email Address!")
    private String email;

    @NotBlank
    @Size(min = 3, max = 9, message = "Please select valid username of length 3-9 characters!")
    @Pattern(regexp = "^[A-Za-z\\d\\s_]+$", message = "Please enter a valid username")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z\\s\\d-']+$",message = "Please enter a valid Firstname")
    @Size(min = 2, max = 30, message = "Please select valid Name of length 2-30 characters!")
    private String firstname;

    private String lastname;

    @NotBlank
    @Size(min = 8, max = 50, message = "Password should not be less than 8 characters!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password should contain Uppercase,Lowercase,Numbers and Special Character")
    private String password;


    @NotEmpty
    private Set<String> roles = new HashSet<>();
}
