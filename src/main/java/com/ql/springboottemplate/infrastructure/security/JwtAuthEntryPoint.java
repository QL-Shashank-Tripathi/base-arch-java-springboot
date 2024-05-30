package com.ql.springboottemplate.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ql.springboottemplate.application.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        int errorCode = HttpServletResponse.SC_UNAUTHORIZED; // Default for unauthorized access
        String message = "Unauthorized";

        if (authException != null) {
            // Handle specific exceptions for bad credentials or other scenarios
            if (authException instanceof BadCredentialsException) {
                errorCode = HttpServletResponse.SC_FORBIDDEN; // Or a more specific code
                message = "Invalid username or password";
            }

        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorCode);

        ApiResponse apiResponse = ApiResponse.builder().success(false).status(errorCode).message(message).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),apiResponse);
    }

}
