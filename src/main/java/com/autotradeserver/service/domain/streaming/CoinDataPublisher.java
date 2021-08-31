package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.startIdx.StartIdx;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Flow.Publisher;

@Configuration
@RequiredArgsConstructor
public class CoinDataPublisher{

    private final StartIdx startIdx;
    private final StreamData streamData;
//    private final CoinDataSubscriber coinDataSubscriber;

    public void makeSubscriptionObj(final String url)
            throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

    public void subscribe(final String msg){
       publishMsg(msg).subscribe(
               new CoinDataSubscriber(startIdx)
       );
    }

    private Publisher<JSONArray> publishMsg(final String msg){
        return subscriber -> subscriber.onSubscribe(
                new CoinDataSubscription(msg, startIdx, streamData, subscriber)
        );
    }
}
