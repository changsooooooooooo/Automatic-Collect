package com.autotradeserver.exceptions;

import java.util.concurrent.ExecutionException;

public class CompletableFutureException extends ExecutionException {
    public CompletableFutureException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
