package com.autotradeserver.controller;

import com.neovisionaries.ws.client.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CoinControllerTest {

    @Test
    @DisplayName("Web SocketTest")
    void webSocketPG() throws WebSocketException, IOException, InterruptedException, ExecutionException, URISyntaxException {
        ExecutorService es = Executors.newSingleThreadExecutor();

        WebSocket ws = new WebSocketFactory()
                .setConnectionTimeout(Integer.MAX_VALUE)
                .createSocket("wss://api.upbit.com/websocket/v1")
                .addListener(new WebSocketAdapter(){
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
                        String str = new String(binary);
                        System.out.println(str);
                    }

                    public void onTextMessage(WebSocket websocket, String message) {
                        System.out.println(message);
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();

        String msg = "[{\"ticket\":\"kor\"},{\"type\":\"trade\",\"codes\":[\"KRW-BTG\"]}]";
        String msg2 = "[{\"ticket\":\"kor\"},{\"type\":\"trade\",\"codes\":[\"KRW-ETH\"]}]";
        String msg3 = "[{\"ticket\":\"kor\"},{\"type\":\"trade\",\"codes\":[\"KRW-BTC\"]}]";

        WebSocketClient webSocketClient = new WebSocketClient(new URI("wss://api.upbit.com/websocket/v1")) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                this.send("Hello");
            }

            @Override
            public void onMessage(String message) {
                if (message.equals("Hello")) {
                    this.close();
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
            }

        };
        webSocketClient.connect();
    }

}
