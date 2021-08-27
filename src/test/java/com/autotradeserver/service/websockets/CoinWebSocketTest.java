package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocketException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class CoinWebSocketTest {

    @Autowired
    private CoinWebSocket coinWebSocket;

    @Test
    @DisplayName("Return Recent Value Test")
    void returnRecentValue() throws WebSocketException, IOException, ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();

        coinWebSocket.createWS("wss://api.upbit.com/websocket/v1");
        JSONObject json = coinWebSocket.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
        System.out.println(json);
    }

}
