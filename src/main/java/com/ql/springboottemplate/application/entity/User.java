package com.ql.springboottemplate.application.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Indexed(unique = true)
    private String username;

    @NotBlank
    private String firstname;

    private String lastname;

    @NotBlank
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

}
