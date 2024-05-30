package com.ql.springboottemplate.application.repository;

import com.ql.springboottemplate.application.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
