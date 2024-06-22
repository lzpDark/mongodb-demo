package com.example.mongodbdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;


/**
 *  sharding collection using userId as ranged sharding key:
 *  sh.shardingCollection("test.post", {userId: 1})
 */
@Data
public class Post {

    @Id
    private String id;
    private String userId;
    private String title;
    private List<String> tags;
    private String content;
    private Date createdTime;
    private Date updatedTime;
}
