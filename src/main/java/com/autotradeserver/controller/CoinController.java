package com.autotradeserver.controller;

import com.autotradeserver.dto.coinsector.CoinSendDTO;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.streaming.CoinDataPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/coin")
@RequiredArgsConstructor
public class CoinController {

    private final CoinSendDTO coinSendDTO;
    private final CoinDataPublisher coinDataPublisher;

    @Value("${coin.url}")
    private String url;

    @GetMapping("")
    public Map<String, String> healthCheck(){
        return Map.of(
                "Health", HttpStatus.OK.name()
        );
    }

    @GetMapping("/trade")
    public String streamCoinInfo(@RequestParam(value="theme") String theme)
            throws SocketConnectException, SocketCreateException, ExecutionException, InterruptedException {
        String msgJsonList = coinSendDTO.makeMsgList(theme);
        coinDataPublisher.makeSubscriptionObj(url);
        coinDataPublisher.subscribe(msgJsonList);
        return coinDataPublisher.sendStreamData();
    }
}
