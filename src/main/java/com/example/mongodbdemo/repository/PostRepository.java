package com.example.mongodbdemo.repository;

import com.example.mongodbdemo.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
