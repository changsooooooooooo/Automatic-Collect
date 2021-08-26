package com.autotradeserver.dto;

import com.autotradeserver.service.domain.StreamData;

import java.util.concurrent.Flow.*;

public class CoinDataSubscriber implements Subscriber<StreamData> {
    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(StreamData item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
