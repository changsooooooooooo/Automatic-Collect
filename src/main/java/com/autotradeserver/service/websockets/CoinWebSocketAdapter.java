package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    private JSONObject jsonObject;
    private static ExecutorService es = Executors.newSingleThreadExecutor();

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
        String text = new String(binary);
        jsonObject = new JSONObject(text);
    }

    @Override
    public void onTextMessage(WebSocket websocket, String text) {
        jsonObject = new JSONObject(text);
    }

    public JSONObject returnCurrentJson(){
        return jsonObject;
    }

}
