package com.autotradeserver.service.websockets;

import com.autotradeserver.dto.coinsector.CoinTradeDTO;
import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.repository.CoinTradeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private CoinTradeDTO coinDataDTO;
    private CompletableFuture<JSONObject> completableFuture;
    private final CoinTradeRepository coinRepository;
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        log.info("Real Json : {}", json);
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
