package com.autotradeserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:variables.properties")
public class Configs {

    private final Environment env;

    public String getValue(String key){
        return env.getProperty(key);
    }
}
