package com.example.mongodbdemo.config;

import com.example.mongodbdemo.cron.SampleCronJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : lzp
 */
@EnableScheduling
@Configuration
public class CronJobConfig {

    @Bean
    public SampleCronJob sampleCronJob() {
        return new SampleCronJob();
    }
}
