package com.autotradeserver.service.domain;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.websockets.CoinWebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class StreamData {

    private final CoinWebSocket coinWebSocket;
    private final ExecutorService es = Executors.newCachedThreadPool();

    public void createSocket(String url) throws SocketCreateException, SocketConnectException {
        try {
            coinWebSocket.createWS(url);
        } catch (IOException e) {
            throw new SocketCreateException("Not Correct URL", url);
        } catch (WebSocketException e) {
            throw new SocketConnectException(e.getError(), "Cannot Connect!");
        }
    }

    public JSONObject returnCurrentMsg(String msg){
        return new JSONObject();
    }
}
