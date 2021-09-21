package com.autotradeserver.service.websockets;

import com.amazonaws.services.s3.AmazonS3;
import com.autotradeserver.config.AWSS3Config;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${coin.base-cutoff}")
    private int cutoff;

    private final AWSS3Config awss3Config;
    private CompletableFuture<JSONObject> completableFuture;

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        log.info("Real Json : {}", json);
        completableFuture.complete(json);
    }

    public JSONObject returnCurrentJson() {
        completableFuture = new CompletableFuture<>();
        return completableFuture.join();
    }
}
