package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinDataPublisherTest {


    @Autowired
    private StreamData streamData;
    @Autowired
    private CoinDataSubscriber coinDataSubscriber;
    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Test
    @DisplayName("CoinDataPublish Test")
    void publishAbleTest() throws SocketConnectException, SocketCreateException {
        coinDataPublisher.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
        coinDataPublisher.subscribe("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
    }
}
