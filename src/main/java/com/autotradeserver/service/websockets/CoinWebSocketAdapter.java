package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Getter
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private CompletableFuture<JSONObject> completableFuture;
    private final static ExecutorService es = Executors.newCachedThreadPool();

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary){
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        log.info("Json : {}", json);
        completableFuture.complete(json);
    }

    public JSONObject returnCurrentJson() throws CompletableFutureException, CompletableFutureInterruptException {
        completableFuture = new CompletableFuture<>();
        try {
            return completableFuture.get();
        } catch (InterruptedException e) {
            throw new CompletableFutureInterruptException("Future Interrupted!");
        } catch (ExecutionException e) {
            throw new CompletableFutureException("Future Exception!", e.getCause());
        }
    }
}
