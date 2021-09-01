package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.startIdx.StartIdx;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoinDataPublisherTest {

    @Autowired
    private StartIdx strIdx;
    @Autowired
    private StreamData streamData;
    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Test
    @DisplayName("CoinDataPublish Test")
    void publishAbleTest() throws SocketConnectException, SocketCreateException {
        coinDataPublisher.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
        for(int i = 0; i<100; i++){
            coinDataPublisher.subscribe("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
        }
    }
}
