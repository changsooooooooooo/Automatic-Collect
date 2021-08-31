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
import java.util.concurrent.ExecutionException;

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
        JSONObject list = cws.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
        System.out.println(list);
    }

    @Test
    @DisplayName("BufferTest")
    void returnCurMsg2(){
        List<Integer> x = new ArrayList<>();
        x.add(3);
        x.add(4);
        List<Integer> y = new ArrayList<>();
        y.add(1);
        y.add(3);
        x.addAll(y);
        y = new ArrayList<>();
        System.out.println(x);
    }

}
