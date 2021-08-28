package com.autotradeserver.exceptions;

import java.io.IOException;

public class SocketCreateException extends IOException {

    private final String url;

    public SocketCreateException(String message, String url){
        super(message);
        this.url = url;
    }
}
