package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocket {

    private WebSocket ws;
    private final CoinWebSocketAdapter coinWebSocketAdapter;

    public void createWS(final String url)
            throws IOException, WebSocketException {
        ws = new WebSocketFactory()
        .setConnectionTimeout(Integer.MAX_VALUE)
        .createSocket(url)
        .addListener(coinWebSocketAdapter)
        .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
        .connect();
    }

    private void sendMessage(final String msg) {
        ws.sendText(msg);
    }

    public JSONObject getRecentMessage(final String msg) {
        sendMessage(msg);
        return coinWebSocketAdapter.returnCurrentJson();
    }
}
