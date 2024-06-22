package com.example.mongodbdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private List<String> permissions;
}
