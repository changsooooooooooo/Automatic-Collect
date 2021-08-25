package com.autotradeserver.exceptions;

import com.neovisionaries.ws.client.WebSocketError;
import com.neovisionaries.ws.client.WebSocketException;
import lombok.Getter;

public class SocketConnectException extends WebSocketException {
    public SocketConnectException(WebSocketError error, String message) {
        super(error, message);
    }
}
