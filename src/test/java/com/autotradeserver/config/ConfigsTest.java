package com.autotradeserver.config;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class ConfigsTest {

    private Environment environment;

    @Test
    void testEnvProps(){
        System.out.println(environment.getProperty("coin.url"));
    }

}
