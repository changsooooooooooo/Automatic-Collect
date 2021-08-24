package com.autotradeserver.controller;

import com.neovisionaries.ws.client.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Log4j2
public class CoinController {

    @GetMapping("/btg")
    public String returnCoinTicker() throws IOException, WebSocketException {
        WebSocket ws = new WebSocketFactory()
                .setConnectionTimeout(Integer.MAX_VALUE)
                .createSocket("wss://api.upbit.com/websocket/v1")
                .addListener(new WebSocketAdapter(){
                    @Override
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
                        String text = new String(binary);
                        log.info("message : {}", text);
                    }
                    @Override
                    public void onTextMessage(WebSocket websocket, String message) {
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
        String msg = "[{\"ticket\":\"kor\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTG\"]}]";
        ws.sendText(msg);
        return "";
    }

}
