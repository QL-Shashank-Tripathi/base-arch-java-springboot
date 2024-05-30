package com.ql.springboottemplate.application.controller;


import com.ql.springboottemplate.application.dto.response.ApiResponse;
import com.ql.springboottemplate.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse getAllUser(){
        log.info("Getting all users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getUserbyId(@PathVariable String id){
        return userService.findById(id);
    }




}
