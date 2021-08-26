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

    private static WebSocket ws;
    private final List<JSONObject> returnList;

    public void createWS(String url) throws IOException, WebSocketException {
        ws = new WebSocketFactory()
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

    public void sendMessage(String msg){
        ws.sendText(msg);
    }

    public JSONObject returnRecentValue(){
        int size = returnList.size();
        return returnList.get(size-1);
    }
}
