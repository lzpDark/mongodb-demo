package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.model.Post;
import com.example.mongodbdemo.repository.PostRepository;
import com.example.mongodbdemo.security.authorization.PermissionChecker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Resource
    private PostRepository postRepository;

    @PermissionChecker(permissions = {
            "posts:create"
    })
    @PostMapping("")
    public Object add(@RequestBody Post post) {
        Date date = new Date();
        post.setCreatedTime(date);
        post.setUpdatedTime(date);
        return postRepository.insert(post);
    }

    @PermissionChecker(permissions = {
            "posts:view"
    })
    @GetMapping("")
    public List<Post> list() {
        return postRepository.findAll();
    }

}
