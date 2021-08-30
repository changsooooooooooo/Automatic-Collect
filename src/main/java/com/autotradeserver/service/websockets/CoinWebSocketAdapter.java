package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private CompletableFuture<JSONObject> completableFuture;
    private static final ExecutorService es = Executors.newCachedThreadPool();

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary){
        es.submit(
                ()->{
                    String text = new String(binary);
                    JSONObject msgJsonObject = new JSONObject(text);
                    log.info("Real Json : {}", msgJsonObject);
                    completableFuture.complete(msgJsonObject);
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
