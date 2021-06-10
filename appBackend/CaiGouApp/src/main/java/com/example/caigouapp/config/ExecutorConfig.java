package com.example.caigouapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置线程池，用来完成每日菜谱推荐任务
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    private static int MAXPOOLSIZE = 100;//最大线程数
    private static int COREPOOLSIZE = 10;//核心线程数
    private static int QUEUECAPACITY = 10; //任务队列的大小
    private static int KEEPALIVE = 30;//线程存活时间
    private static String THREADNAME = "QuartzMenu";//线程前缀名

    @Bean
    public Executor qutarzExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(COREPOOLSIZE);
        executor.setMaxPoolSize(MAXPOOLSIZE);
        executor.setQueueCapacity(QUEUECAPACITY);
        executor.setKeepAliveSeconds(KEEPALIVE);
        executor.setThreadNamePrefix(THREADNAME);

        return  executor;
    }
}
