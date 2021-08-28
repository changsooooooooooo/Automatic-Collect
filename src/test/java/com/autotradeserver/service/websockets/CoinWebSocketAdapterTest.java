package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocketException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class CoinWebSocketAdapterTest {

    @Autowired
    private CoinWebSocket cws;

    @Test
    @DisplayName("Future onBinaryMessage Test")
    void returnMsgTest() throws WebSocketException, IOException, ExecutionException, InterruptedException {
        cws.createWS("wss://api.upbit.com/websocket/v1");
        JSONObject json = cws.getRecentMessage("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]");
        System.out.println("Json Object : "+json);
    }

}
