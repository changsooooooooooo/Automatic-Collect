package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Log4j2
@SpringBootTest
class CoinDataSubscriptionTest {

    @Autowired
    private CoinDataSubscriber coinDataSubscriber;

    @Autowired
    private StreamData streamData;

    private final CoinDataSubscription coinDataSubscription = new CoinDataSubscription("KRW-EOS", streamData, coinDataSubscriber);

    @Test
    @DisplayName("Subscribe Test")
    void subscribingTest() throws SocketConnectException, SocketCreateException {
        coinDataSubscription.makeSubscriptionObj("wss://api.upbit.com/websocket/v1");
        coinDataSubscriber.onSubscribe(coinDataSubscription);
    }

    @Test
    @DisplayName("RequestMethodType Test")
    void howToRequestTest() throws SocketConnectException, SocketCreateException, ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newSingleThreadExecutor();
        Future<?> x = executors.submit(
                ()-> coinDataSubscriber.onNext(new JSONObject())
        );
        System.out.println("x : "+x.toString());
    }

}
