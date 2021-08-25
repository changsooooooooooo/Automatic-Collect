package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinWebSocket {

    private final List<JSONObject> returnList;

    public WebSocket createWS(String url) throws IOException, WebSocketException {
        return new WebSocketFactory()
                .setConnectionTimeout(Integer.MAX_VALUE)
                .createSocket(url)
                .addListener(new WebSocketAdapter(){
                    @Override
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
                        String text = new String(binary);
                        returnList.add(new JSONObject(text));
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }

    public JSONObject returnRecentValue(){
        int size = returnList.size();
        return returnList.get(size-1);
    }
}
