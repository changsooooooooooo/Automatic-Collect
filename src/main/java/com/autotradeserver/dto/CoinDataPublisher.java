package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.Flow.Publisher;

@Service
@RequiredArgsConstructor
public class CoinDataPublisher{

    private final StreamData streamData;

    public void makeSubscriptionObj(String url) throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

    public String subscribe(String msg){
       publishMsg(msg).subscribe(
               new CoinDataSubscriber()
       );
       return "";
    }

    private Publisher<JSONObject> publishMsg(String msg){
        return subscriber -> subscriber.onSubscribe(
                new CoinDataSubscription(msg, streamData, subscriber)
        );
    }
}
