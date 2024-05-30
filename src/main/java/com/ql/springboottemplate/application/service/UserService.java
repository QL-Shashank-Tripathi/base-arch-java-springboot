package com.ql.springboottemplate.application.service;




import com.ql.springboottemplate.application.dto.request.UserDto;
import com.ql.springboottemplate.application.dto.response.ApiResponse;
import com.ql.springboottemplate.application.entity.User;

import java.util.Optional;

public interface UserService {
 public ApiResponse saveUser(UserDto userDto);

 public ApiResponse findById(String id);

 public ApiResponse findAll();

 public Optional<User> findByEmail(String email);




}
