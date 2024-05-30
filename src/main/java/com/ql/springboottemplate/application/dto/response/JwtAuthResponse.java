package com.ql.springboottemplate.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    public JwtAuthResponse(String token){
        this.accessToken=token;
    }


}
