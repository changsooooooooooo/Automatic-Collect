package com.autotradeserver.service.domain.streaming;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class CoinDataSubscriber implements Subscriber<JSONObject> {

    private Subscription subscription;

    @Override
    public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1L);
    }

    @Override
    public void onNext(final JSONObject item) {
        log.info("Item : {}", item);
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
