package com.autotradeserver.service.domain.streaming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoinDataSubscriberTest {

    @Test
    void concatStringTest(){
        String x = "/"+"aws"+"/"+4;
        System.out.println("x is : " + x);
    }

}
