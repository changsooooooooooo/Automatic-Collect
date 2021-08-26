package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscription;

@Log4j2
@RequiredArgsConstructor
public class CoinDataSubscription implements Subscription {

    private final String msg;
    private final StreamData streamData;
    private final CoinDataSubscriber coinDataSubscriber;
    private final ExecutorService executors = Executors.newSingleThreadExecutor();

    public void makeSubscriptionObj(String url) throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

    @Override
    public void request(long n) {
        executors.submit(
                () -> coinDataSubscriber.onNext(
                        streamData.returnCurrentMsg(msg)
                )
        );
    }

    @Override
    public void cancel() {
        coinDataSubscriber.onComplete();
    }
}
