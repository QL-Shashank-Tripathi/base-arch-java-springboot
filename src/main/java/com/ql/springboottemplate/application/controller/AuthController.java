package com.ql.springboottemplate.application.controller;

import com.ql.springboottemplate.application.dto.request.LoginDto;
import com.ql.springboottemplate.application.dto.request.UserDto;
import com.ql.springboottemplate.application.dto.response.ApiResponse;
import com.ql.springboottemplate.application.dto.response.JwtAuthResponse;
import com.ql.springboottemplate.application.service.UserService;
import com.ql.springboottemplate.infrastructure.security.JwtUtil;
import com.ql.springboottemplate.shared.constants.Constant;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,   UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }




    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {

        log.info("Entered into /login");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication);
        return new ResponseEntity<>(ApiResponse.builder().success(true).status(Constant.SUCCESS).message("Login Success & " +
                "token generated").data(new JwtAuthResponse(token)).build(), HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody @Valid UserDto userDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(ApiResponse.builder().success(false).status(Constant.BAD_REQUEST).message(result.getFieldError().getDefaultMessage()).build(),HttpStatus.BAD_REQUEST);
        }

        ApiResponse apiResponse = userService.saveUser(userDto);
        if(apiResponse.getData()!=null)
            return new ResponseEntity<>(apiResponse,HttpStatus.valueOf(Constant.SUCCESS));
        else
            return new ResponseEntity<>(apiResponse,HttpStatus.valueOf(Constant.FAILED_REQUEST));
    }

}
