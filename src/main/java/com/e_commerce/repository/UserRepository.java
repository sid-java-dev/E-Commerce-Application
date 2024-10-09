package com.e_commerce.repository;

import com.e_commerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String emailOrPhone);
}
