package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
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
    private CompletableFuture<JSONObject> completableFuture;
    private static final ExecutorService es = Executors.newCachedThreadPool();

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

    public JSONObject returnCurrentJson() throws CompletableFutureException, CompletableFutureInterruptException {
        completableFuture = new CompletableFuture<>();
        try{
            return completableFuture.get();
        }catch(ExecutionException e){
            throw new CompletableFutureException("Future Execution Error", e.getCause());
        }catch(InterruptedException e){
            throw new CompletableFutureInterruptException("Interrupt Exception!");
        }
    }
}
