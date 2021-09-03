package com.autotradeserver.service.websockets;

import com.autotradeserver.dto.coinsector.CoinDataDTO;
import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.repository.CoinRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private CoinDataDTO coinDataDTO;
    private CompletableFuture<JSONObject> completableFuture;
    private final CoinRepository coinRepository;
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws JsonProcessingException {
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        Long time = json.getLong("timestamp");
        json.put("current_time", dateFormat.format(new Date(time)));
        coinDataDTO = objectMapper.readValue(json.toString(), CoinDataDTO.class);
        coinRepository.save(coinDataDTO);
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
