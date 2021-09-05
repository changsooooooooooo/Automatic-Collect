package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.neovisionaries.ws.client.WebSocketException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootTest
class CoinWebSocketAdapterTest {

    @Autowired
    private CoinWebSocket cws;

    @Autowired
    private CoinWebSocketAdapter coinWebSocketAdapter;

    @Test
    @DisplayName("Future onBinaryMessage Test")
    void returnMsgTest() throws WebSocketException, IOException, ExecutionException, InterruptedException {
        cws.createWS("wss://api.upbit.com/websocket/v1");
        JSONObject jsonPacketArr = cws.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
        System.out.println("Json Object : "+jsonPacketArr);
    }

    @Test
    @DisplayName("BufferTest")
    void returnCurMsg() throws WebSocketException, IOException, CompletableFutureException, CompletableFutureInterruptException {
        cws.createWS("wss://api.upbit.com/websocket/v1");

        List<String> jsonList = new ArrayList<>();
        jsonList.add("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-POLY\"], \"isOnlyRealtime\":1}]");
        jsonList.add("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"], \"isOnlyRealtime\":1}]");

        CompletableFuture future = jsonList.stream()
                .map(msg -> CompletableFuture.supplyAsync(
                        ()->

                ))
                .collect(Collectors.toList());

    }

    @Test
    @DisplayName("Return Condition Check TDD")
    void returnConditionCheckTest() throws WebSocketException, IOException, CompletableFutureException, CompletableFutureInterruptException {
        cws.createWS("wss://api.upbit.com/websocket/v1");
        cws.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"orderbook\",\"codes\":[\"KRW-BTC\"], \"isOnlyRealtime\":1}]");
        for(int i = 0; i<1000; i++){
            cws.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"], \"isOnlyRealtime\":1}]");
        }
    }
}
