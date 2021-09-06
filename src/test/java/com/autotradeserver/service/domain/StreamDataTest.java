package com.autotradeserver.service.domain;

import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootTest
class StreamDataTest {

    @Autowired
    private StreamData streamData;

    @Test
    @DisplayName("Create StreamData Test")
    void testConstructorTest() throws SocketConnectException, SocketCreateException {
        streamData.createSocket("wss://api.upbit.com/websocket/v1");
        System.out.println(streamData);
    }

    @Test
    @DisplayName("Send Msg Test")
    void sendMsgTest() throws SocketConnectException, SocketCreateException, ExecutionException, InterruptedException {
        streamData.createSocket("wss://api.upbit.com/websocket/v1");
        JSONObject json = streamData.returnCurrentMsg("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-ETH\"], \"isOnlyRealtime\":1}]");
        System.out.println(json);
    }

    @Test
    @DisplayName("Send Non-Blocking Test")
    void sendNonBlockingTest() throws SocketConnectException, SocketCreateException, CompletableFutureException, CompletableFutureInterruptException {
        streamData.createSocket("wss://api.upbit.com/websocket/v1");

        List<String> jsonList = new ArrayList<>();
        jsonList.add("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-POLY\"], \"isOnlySnapshot\":1}]");
        jsonList.add("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"], \"isOnlySnapshot\":1}]");

        List<CompletableFuture<JSONObject>> x = jsonList.stream()
                .map(msg-> CompletableFuture.supplyAsync(
                        ()-> streamData.returnCurrentMsg(msg)
                ))
                .collect(Collectors.toList());

        x.stream()
                .map(future->future.join())
                .forEach(json-> System.out.println(json));
    }
}
