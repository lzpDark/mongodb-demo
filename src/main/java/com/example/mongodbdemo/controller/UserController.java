package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.model.User;
import com.example.mongodbdemo.repository.UserRepository;
import com.example.mongodbdemo.security.authorization.PermissionChecker;
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

    @PermissionChecker(permissions = {
            "users:create"
    })
    @PostMapping("")
    public Object create(@RequestBody User user) {
        return userRepository.insert(user);
    }

    @PermissionChecker(permissions = {
            "users:view"
    })
    @GetMapping("/{userId}")
    public Optional<User> get(@PathVariable("userId") String userId) {
        return userRepository.findById(userId);
    }
}
