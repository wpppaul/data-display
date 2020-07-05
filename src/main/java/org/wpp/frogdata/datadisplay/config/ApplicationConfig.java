package org.wpp.frogdata.datadisplay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 * Pack:        org.wpp.frogdata.datadisplay.config
 * File:        ApplicationConfig
 * User:        wupp
 * CreateTime:  2020-07-04 17:00
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPool(){
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(100);
        threadPool.setMaxPoolSize(150);
        threadPool.setQueueCapacity(5000);
        threadPool.setKeepAliveSeconds(360);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPool;
    }
}
