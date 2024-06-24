package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.service.ServiceA;
import com.example.mongodbdemo.service.ServiceB;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ServiceA serviceA;
    private ServiceB serviceB;

    @Resource
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
    @Resource
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    // TODO: using some other methods to impl
    @GetMapping("")
    public Object test() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Future<List<String>> resultA = executorService.submit(() -> {
            List<String> res;
            try {
                res = serviceA.getValues();
            } finally {
                countDownLatch.countDown();
            }
            return res;
        });
        Future<List<String>> resultB = executorService.submit(()-> {
            List<String> res;
            try {
                res = serviceB.getValues();
            } finally {
                countDownLatch.countDown();
            }
            return res;
        });
        if (!countDownLatch.await(6, TimeUnit.SECONDS)) {
            // error
            return "timeout";
        }
        // TODO: do some other works
        String result = "test: \n" + Arrays.toString(resultA.get().toArray()) + "\n" + Arrays.toString(resultB.get().toArray());
        long costTime = System.currentTimeMillis() - startTime;
        log.info("cost time: {} ms", costTime);
        return result;
    }
}
