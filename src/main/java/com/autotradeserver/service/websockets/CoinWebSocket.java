package com.autotradeserver.service.websockets;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CoinWebSocket {

    private WebSocket ws;
    private final CoinWebSocketAdapter coinWebSocketAdapter;

    public void createWS(String url)
            throws IOException, WebSocketException {
        ws = new WebSocketFactory()
        .setConnectionTimeout(Integer.MAX_VALUE)
        .createSocket(url)
        .addListener(coinWebSocketAdapter)
        .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
        .connect();
    }

    private void sendMessage(String msg) {
        ws.sendText(msg);
    }

    public JSONArray getRecentMessage(String msg)
            throws CompletableFutureException, CompletableFutureInterruptException {
        sendMessage(msg);
        return coinWebSocketAdapter.returnCurrentJson();
    }
}
