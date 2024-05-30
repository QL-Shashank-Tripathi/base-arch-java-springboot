package com.ql.springboottemplate.application.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController extends BaseController {

    @GetMapping
    public String helloWorld() {
        return "Hello User";
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/hello-mod")
    public String helloWorld2() {
        return "Hello Mod";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hello-admin")
    public String helloWorld3() {
        return "Hello Admin";
    }

}
