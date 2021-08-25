package com.autotradeserver.service.domain;

import com.autotradeserver.service.websockets.CoinWebSocket;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import exceptions.SocketCreateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StreamData {

    private final CoinWebSocket coinWebSocket;

    public void createSocket(Integer timeOut, String url) throws SocketCreateException {
        WebSocket ws;
        try{
            ws = coinWebSocket.createWS(timeOut, url);
        } catch (IOException e) {
            throw new SocketCreateException("check create socket url", url);
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }
}
