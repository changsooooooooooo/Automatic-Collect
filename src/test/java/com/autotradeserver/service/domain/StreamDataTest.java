package com.autotradeserver.service.domain;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
}
