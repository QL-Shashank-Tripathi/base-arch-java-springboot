package com.ql.springboottemplate.application.service.impl;



import com.ql.springboottemplate.application.dto.request.UserDto;
import com.ql.springboottemplate.application.dto.response.ApiResponse;
import com.ql.springboottemplate.application.dto.response.UserResponseDto;
import com.ql.springboottemplate.application.entity.Role;
import com.ql.springboottemplate.application.entity.User;
import com.ql.springboottemplate.application.repository.RoleRepository;
import com.ql.springboottemplate.application.repository.UserRepository;
import com.ql.springboottemplate.application.service.UserService;
import com.ql.springboottemplate.shared.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse saveUser(UserDto userDto) {
        log.info("Saving user");
        User newuser = new User();
        if (!ObjectUtils.isEmpty(userDto)) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Set<String> strroles = userDto.getRoles();
            Set<Role> roles = new HashSet<>();
            strroles.stream().forEach(role ->{
                switch(role){
                    case "admin":
                        Role adminrole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(()->new RuntimeException("Error: Role not found"));
                        roles.add(adminrole);
                        break;
                    case "mod":
                        Role rolemod = roleRepository.findByName("ROLE_MODERATOR").orElseThrow(()->new RuntimeException("Error: Role not found"));
                        roles.add(rolemod);
                        break;
                    default:
                        Role userrole = roleRepository.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Error: Role not found"));
                        roles.add(userrole);
                }
            });
            User user = mapToEntity(userDto);
            user.setRoles(roles);
            try {
                 newuser = userRepository.save(user);
            } catch (Exception e) {
                return ApiResponse.builder().success(false).status(Constant.FAILED_REQUEST).message(e.getMessage()).build();
            }
        }
        return ApiResponse.builder().success(true).status(Constant.SUCCESS).message("User Saved Successfully").data(mapToResponseDto(newuser)).build();

    }

    @Override
    public ApiResponse findAll() {
        log.info("Fetching all users");
        List<UserResponseDto> userResponseDtoList = userRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
        if(!userResponseDtoList.isEmpty()){
            return ApiResponse.builder().success(true).status(Constant.SUCCESS).message("Fetched All users successfully!").data(userResponseDtoList).build();
        }
        else
            return ApiResponse.builder().success(false).status(Constant.NOT_FOUND).message("No user found!!").build();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByUsernameOrEmail(email,email);
    }

    @Override
    public ApiResponse findById(String id) {
        UserResponseDto userResponseDto = this.mapToResponseDto(userRepository.findById(id).orElse(null));
        if(userResponseDto!=null){
            return ApiResponse.builder().success(true).status(Constant.SUCCESS).message("User found with id:"+id).data(userResponseDto).build();
        }
        else
            return ApiResponse.builder().success(false).status(Constant.NOT_FOUND).message("No user found by id: "+id).build();

    }



    public User mapToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto mapToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public UserResponseDto mapToResponseDto(User user){
        return modelMapper.map(user, UserResponseDto.class);
    }
}


