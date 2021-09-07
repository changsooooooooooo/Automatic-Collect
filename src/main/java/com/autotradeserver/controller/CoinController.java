package com.autotradeserver.controller;

import com.autotradeserver.config.Configs;
import com.autotradeserver.dto.coinsector.CoinSendDTO;
import com.autotradeserver.dto.coinsector.CoinsSector;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.service.domain.streaming.CoinDataPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/coin")
@RequiredArgsConstructor
public class CoinController {

    private final Configs config;
    private final CoinSendDTO coinSendDTO;
    private final CoinDataPublisher coinDataPublisher;

    @GetMapping("")
    public Map<String, String> healthCheck(){
        return Map.of(
                "Health", HttpStatus.OK.name()
        );
    }

    @GetMapping("/trade")
    public String streamCoinInfo()
            throws SocketConnectException, IOException, ExecutionException, InterruptedException {
        String url = config.getValue("coin.url");
        coinSendDTO.makeJsonObj();
        String message = coinSendDTO.returnSendMsg("ticker");
        coinDataPublisher.makeSubscriptionObj(url);
        coinDataPublisher.subscribe(message);
        return coinDataPublisher
                .sendStreamData()
                .toString();
    }
}
