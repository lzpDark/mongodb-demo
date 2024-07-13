package com.example.mongodbdemo.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author : lzp
 */
@Slf4j
public class SampleCronJob {

    @Scheduled(cron = "1 * * * * *")
    public void runSample() {
        log.info("running cron sample...");
    }
}
