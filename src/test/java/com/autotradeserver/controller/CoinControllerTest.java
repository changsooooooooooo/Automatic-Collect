package com.autotradeserver.controller;

import com.neovisionaries.ws.client.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CoinControllerTest {

    @Test
    @DisplayName("Web SocketTest")
    void webSocketPG() throws WebSocketException, IOException {
        ExecutorService es = Executors.newSingleThreadExecutor();

        WebSocket ws = new WebSocketFactory()
                .setConnectionTimeout(Integer.MAX_VALUE)
                .createSocket("wss://api.upbit.com/websocket/v1")
                .addListener(new WebSocketAdapter(){
                    @Override
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                        String text = new String(binary);
                        System.out.println(text);
                        System.out.println(websocket);
                    }
                    @Override
                    public void onTextMessage(WebSocket websocket, String message) {
                        System.out.println(websocket);
                        System.out.println(message);
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();

        String msg = "[{\"ticket\":\"kor\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTG\"]}]";
        String msg2 = "[{\"ticket\":\"kor\"},{\"type\":\"ticker\",\"codes\":[\"KRW-ETH\"]}]";
        String msg3 = "[{\"ticket\":\"kor\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"]}]";

        ws = ws.sendText(msg);
        System.out.println(ws);
        ws.disconnect();
    }

}
