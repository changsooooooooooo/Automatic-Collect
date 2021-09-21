package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class CoinDataPublisherTest {

    @Autowired
    private StreamData streamData;
    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Test
    @DisplayName("CoinDataPublish Test")
    void publishAbleTest() throws SocketConnectException, SocketCreateException, ExecutionException, InterruptedException {
        coinDataPublisher.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
        List<String> msgList = new ArrayList<>();

        String msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-POLY\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-ETH\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-EOS\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-THETA\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-NEO\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-WAVES\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-XLM\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
        msg = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-TRX\"], \"isOnlyRealtime\":1}]";
        msgList.add(msg);
    }
}
