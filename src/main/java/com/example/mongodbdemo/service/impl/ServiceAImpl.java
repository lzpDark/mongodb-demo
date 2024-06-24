package com.example.mongodbdemo.service.impl;

import com.example.mongodbdemo.service.ServiceA;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceAImpl implements ServiceA {
    @Override
    public List<String> getValues() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of("A1", "A2");
    }
}
