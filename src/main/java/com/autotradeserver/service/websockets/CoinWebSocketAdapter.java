package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

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
