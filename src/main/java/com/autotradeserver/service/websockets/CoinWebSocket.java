package com.autotradeserver.service.websockets;

import com.neovisionaries.ws.client.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CoinWebSocket {

    private final JSONParser jsonParser;
    private final JSONArray jsonArray;
    private JSONObject jsonObj;

    public WebSocket createWS(Integer timeout, String url) throws IOException, WebSocketException {

        return new WebSocketFactory()
                .setConnectionTimeout(timeout)
                .createSocket(url)
                .addListener(new WebSocketAdapter(){
                    @Override
                    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws ParseException {
                        String text = new String(binary);
                        jsonObj = (JSONObject) jsonParser.parse(text);
                        jsonArray.add(jsonObj);
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }

    public Object returnRecentValue(){
        int length = jsonArray.size();
        return jsonArray.get(length-1);
    }
}
