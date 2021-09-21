package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@RequiredArgsConstructor
public class CoinDataSubscription implements Subscription {

    private final String msg;
    private final StreamData streamData;
    private final Subscriber<? super JSONObject> subscriber;
    private static final ExecutorService es = Executors.newSingleThreadExecutor();

    @Override
    public void request(final long n){
        es.submit(
                () -> subscriber.onNext(streamData.returnCurrentMsg(msg))
        );
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}
