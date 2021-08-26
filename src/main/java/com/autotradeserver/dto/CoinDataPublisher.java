package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinDataPublisher{

    private final StreamData streamData;

    public void makeSubscriptionObj(String url) throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

}
