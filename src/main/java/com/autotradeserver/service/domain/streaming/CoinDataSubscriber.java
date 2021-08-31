package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.startIdx.StartIdx;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class CoinDataSubscriber implements Subscriber<JSONArray> {

    private Subscription subscription;
    private final StartIdx startIdx;

    @Override
    public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1L);
    }

    @Override
    public void onNext(final JSONArray item) {
        startIdx.changeStartIdx(item.length());
        log.info("Item : {}, Idx : {}", item, startIdx.curIdx());
        subscription.request(1L);
    }

    @Override
    public void onError(final Throwable throwable) {
        log.info("Errors {}", throwable.toString());
    }

    @Override
    public void onComplete() {
        log.info("Complete {}", "Done Passing");
    }
}
