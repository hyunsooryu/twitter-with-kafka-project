package me.hyunsoo.eventdemo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    /**
     * Member에 대한 데이터를 이벤트로 발행
     * 해당 이벤트를 처리할 Thread Pool Task Executor
     */
    @Bean
    Executor myAsyncThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        taskExecutor.setThreadNamePrefix("myAsyncThreadPoolTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
