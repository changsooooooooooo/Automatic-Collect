package com.autotradeserver.service.domain.streaming;

import com.amazonaws.services.s3.AmazonS3;
import com.autotradeserver.config.AWSS3Config;
import com.autotradeserver.repository.CoinDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class CoinDataSubscriber implements Subscriber<JSONObject> {

    @Value("${coin.base-cutoff}")
    private int cutOff;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    private JSONObject jsonItem;
    private Subscription subscription;
    private CompletableFuture<JSONObject> completableFuture;

    private final AWSS3Config awss3Config;
    private final CoinDBRepository coinDBRepository;

    @Override
    public void onSubscribe(final Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1L);
    }

    @Override
    public void onNext(final JSONObject item) {
        log.info("Current Item : {}", item);
        jsonItem = item;
        accumulateToS3(jsonItem);
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

    public void accumulateToS3(final JSONObject item){
        AmazonS3 s3 = awss3Config.amazonS3();
        int now = LocalDateTime
                .now()
                .getHour();
        int quarter = now/cutOff;
        String cat = coinDBRepository.findCoinCategory(item.getString("cd"));
        String bucketPath = cat+"/"+item.getString("td")+"/"+quarter+"/"+item.getLong("sid")+".json";
        s3.putObject(bucketName, bucketPath, item.toString());
    }

    public JSONObject sendToClient() throws ExecutionException, InterruptedException {
        completableFuture = new CompletableFuture<>();
        return completableFuture.get();
    }
}
