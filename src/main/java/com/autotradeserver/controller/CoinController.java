package com.autotradeserver.controller;

import com.autotradeserver.config.Configs;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.streaming.CoinDataPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/coin")
@RequiredArgsConstructor
public class CoinController {

    private final Configs config;
    private final CoinDataPublisher coinDataPublisher;

    @GetMapping("")
    public Map<String, String> healthCheck(){
        return Map.of(
                "Health", HttpStatus.OK.name()
        );
    }

    @GetMapping("/trade")
    public String streamCoinInfo()
            throws SocketConnectException, SocketCreateException, ExecutionException, InterruptedException {
        String url = config.getValue("coin.url");
        coinDataPublisher.makeSubscriptionObj(url);
        coinDataPublisher.subscribe("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-BTC\"], \"isOnlyRealtime\":1}]");
        return coinDataPublisher
                .sendStreamData()
                .toString();
    }

}
