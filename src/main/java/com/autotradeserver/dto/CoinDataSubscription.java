package com.autotradeserver.dto;

import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;
import java.util.concurrent.Flow.Subscription;

@Log4j2
@RequiredArgsConstructor
public class CoinDataSubscription  {

    private final String msg;
    private final StreamData streamData;
    private final Subscriber<? super JSONObject> subscriber;
    private final ExecutorService executors = Executors.newSingleThreadExecutor();


//    @Override
//    public void request(long n) {
//        executors.submit(
//                () -> subscriber.onNext(
//                        streamData.returnCurrentMsg(msg)
//                )
//        );
//    }
//
//    @Override
//    public void cancel() {
//        subscriber.onComplete();
//    }
}
