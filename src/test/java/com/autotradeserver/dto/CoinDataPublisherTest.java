package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoinDataPublisherTest {

    @Autowired
    private StreamData streamData;

    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Test
    @DisplayName("Getter Test for Static Variable")
    void testGetter(){
        System.out.println(streamData);
    }

    @Test
    @DisplayName("Combinate with completableFuture for use service")
    void testFuture() throws SocketConnectException, SocketCreateException {
        List<CoinDataPublisher> publisherList = new ArrayList<>();
        coinDataPublisher.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
//        coinDataPublisher.subscribe("KRW-ETH");
    }
}
