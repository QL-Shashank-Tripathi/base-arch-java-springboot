package com.ql.springboottemplate.application.repository;

import com.ql.springboottemplate.application.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
   public Optional<Role> findByName(String name);
}
