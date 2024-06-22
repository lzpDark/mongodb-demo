package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
