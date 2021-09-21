package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
public class CoinDataPublisher{

    private final StreamData streamData;
    private final CoinDataSubscriber coinDataSubscriber;

    public void makeSubscriptionObj(final String url)
            throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

    public void subscribe(final String msg){
       publishMsg(msg).subscribe(
               coinDataSubscriber
       );
    }

    private Publisher<JSONObject> publishMsg(final String msg){
        return subscriber -> subscriber.onSubscribe(
                new CoinDataSubscription(msg, streamData, subscriber)
        );
    }

    public String sendStreamData() throws ExecutionException, InterruptedException {
        return coinDataSubscriber
                .sendToClient()
                .toString();
    }
}
