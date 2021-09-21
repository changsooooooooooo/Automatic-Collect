package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.CoinSendDTO;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoinDataFluxTest {

    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Autowired
    private CoinSendDTO coinSendDTO;

    @Test
    void newPublisherTest() throws SocketConnectException, SocketCreateException {
        List<CoinDataPublisher> publisherList = new ArrayList<>();
        for(int i = 0; i<4; i++){
            coinDataPublisher.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
            publisherList.add(coinDataPublisher);
        }
        publisherList.stream()
                .forEach(x-> System.out.println(x));
    }

}
