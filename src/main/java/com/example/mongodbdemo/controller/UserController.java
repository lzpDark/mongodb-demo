package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.model.User;
import com.example.mongodbdemo.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Resource
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public Object create(@RequestBody User user) {
        return userRepository.insert(user);
    }

    @GetMapping("/{userId}")
    public Optional<User> get(@PathVariable("userId") String userId) {
        return userRepository.findById(userId);
    }
}
