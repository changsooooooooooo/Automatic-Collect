package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Getter
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private JSONArray jsonArr = new JSONArray();
    private CompletableFuture<JSONArray> completableFuture;

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary){
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        log.info("Json : {}", json);
        jsonArr = jsonArr.put(json);
        completableFuture.complete(jsonArr);
    }

    public JSONArray returnCurrentJson(int strIdx) throws CompletableFutureException, CompletableFutureInterruptException {
        makeSubJsonArr(strIdx);
        completableFuture = new CompletableFuture<>();
        try {
            return completableFuture.get();
        } catch (InterruptedException e) {
            throw new CompletableFutureInterruptException("Future Interrupted!");
        } catch (ExecutionException e) {
            throw new CompletableFutureException("Future Exception!", e.getCause());
        }
    }

    public void makeSubJsonArr(int strIdx){
        List<Object> jsonList = jsonArr.toList()
                .subList(strIdx, jsonArr.length());
        jsonArr = new JSONArray(jsonList);
    }
}
