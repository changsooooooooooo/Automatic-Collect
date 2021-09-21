package com.autotradeserver.service.domain.streaming;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class CoinDataSubscriber implements Subscriber<JSONObject> {

    private JSONObject jsonItem;
    private Subscription subscription;
    private CompletableFuture<JSONObject> completableFuture;

    @Override
    public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1L);
    }

    @Override
    public void onNext(final JSONObject item) {
        log.info("Current Item : {}", item);
        jsonItem = item;
        completableFuture.complete(jsonItem);
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

    public JSONObject sendToClient() throws ExecutionException, InterruptedException {
        completableFuture = new CompletableFuture<>();
        return completableFuture.get();
    }
}
