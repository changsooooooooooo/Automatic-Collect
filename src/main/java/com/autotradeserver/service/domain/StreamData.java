package com.autotradeserver.service.domain;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.websockets.CoinWebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StreamData {

    private final CoinWebSocket coinWebSocket;

    public void createSocket(String url)
            throws SocketCreateException, SocketConnectException {
        try {
            coinWebSocket.createWS(url);
        } catch (IOException e) {
            throw new SocketCreateException("Not Correct URL", url);
        } catch (WebSocketException e) {
            throw new SocketConnectException(e.getError(), "Cannot Connect!");
        }
    }

    public JSONArray returnCurrentMsg(String msg, int strIdx)
            throws CompletableFutureException, CompletableFutureInterruptException {
        return coinWebSocket.getRecentMessage(msg, strIdx);
    }
}
