package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private JSONObject jsonObject;
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static final CompletableFuture<JSONObject> completableFuture = new CompletableFuture<>();

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary){
        es.submit(
                ()->{
                    String text = new String(binary);
                    jsonObject = new JSONObject(text);
                    completableFuture.complete(jsonObject);
                    return null;
                }
        );
    }

    public JSONObject returnCurrentJson() throws ExecutionException, InterruptedException {
        return completableFuture.get();
    }

}
