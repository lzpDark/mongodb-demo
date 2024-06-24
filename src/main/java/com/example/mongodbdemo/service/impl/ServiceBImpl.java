package com.example.mongodbdemo.service.impl;

import com.example.mongodbdemo.service.ServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceBImpl implements ServiceB {
    @Override
    public List<String> getValues() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of("B1", "B2");
    }
}
