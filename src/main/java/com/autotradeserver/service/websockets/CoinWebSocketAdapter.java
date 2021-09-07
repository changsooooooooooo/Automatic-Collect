package com.autotradeserver.service.websockets;

import com.autotradeserver.dto.coinsector.CoinTradeDTO;
import com.autotradeserver.repository.CoinTradeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private CoinTradeDTO coinTradeDataDTO;
    private CompletableFuture<JSONObject> completableFuture;
    private final CoinTradeRepository coinRepository;
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws JsonProcessingException {
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        json.put("current_time", dateFormat.format(new Date()));
        coinTradeDataDTO = objectMapper.readValue(json.toString(), CoinTradeDTO.class);
        coinRepository.save(coinTradeDataDTO);
        log.info("Real Json : {}", json);
        completableFuture.complete(json);
    }

    public JSONObject returnCurrentJson() {
        completableFuture = new CompletableFuture<>();
        return completableFuture.join();
    }
}
