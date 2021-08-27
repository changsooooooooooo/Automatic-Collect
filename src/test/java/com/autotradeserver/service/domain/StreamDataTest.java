package com.autotradeserver.service.domain;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.websockets.CoinWebSocket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StreamDataTest {

    @MockBean
    private StreamData streamData;

    @Test
    @DisplayName("Create StreamData Test")
    void testConstructorTest() throws SocketConnectException, SocketCreateException {
        streamData.createSocket("wss://api.upbit.com/websocket/v1");
        System.out.println(streamData);
    }

}
