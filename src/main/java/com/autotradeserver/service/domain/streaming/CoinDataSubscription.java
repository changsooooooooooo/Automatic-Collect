package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.startIdx.StartIdx;
import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

@Log4j2
@RequiredArgsConstructor
public class CoinDataSubscription implements Subscription {

    private final String msg;
    private final StartIdx startIdx;
    private final StreamData streamData;
    private final Subscriber<? super JSONArray> subscriber;
    private static final ExecutorService es = Executors.newSingleThreadExecutor();

    @Override
    public void request(final long n){
        int idx = startIdx.curIdx();
        es.submit(
                () -> {
                    try {
                        subscriber.onNext(
                                streamData.returnCurrentMsg(msg, idx)
                        );
                    } catch (CompletableFutureException e) {
                        e.printStackTrace();
                    } catch (CompletableFutureInterruptException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}
