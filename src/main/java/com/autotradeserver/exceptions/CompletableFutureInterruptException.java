package com.autotradeserver.exceptions;

public class CompletableFutureInterruptException extends InterruptedException{

    public CompletableFutureInterruptException(String msg){
        super(msg);
    }
}
