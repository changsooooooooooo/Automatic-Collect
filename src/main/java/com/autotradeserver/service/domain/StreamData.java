package com.autotradeserver.service.domain;

import com.autotradeserver.service.websockets.CoinWebSocket;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StreamData {

    private final CoinWebSocket coinWebSocket;

    public void createSocket(String url) throws SocketCreateException, SocketConnectException {
        try {
            WebSocket ws = coinWebSocket.createWS(url);
        } catch (IOException e) {
            throw new SocketCreateException("Not Correct URL", url);
        } catch (WebSocketException e) {
            throw new SocketConnectException(e.getError(), "Cannot Connect!");
        }
    }
}
