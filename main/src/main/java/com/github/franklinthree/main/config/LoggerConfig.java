package com.github.franklinthree.main.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    @Bean(name = "serviceLogger")
    public Logger serviceLogger(){
        Logger serviceLogger = LoggerFactory.getLogger("serviceLogger");
        return serviceLogger;
    }
}
