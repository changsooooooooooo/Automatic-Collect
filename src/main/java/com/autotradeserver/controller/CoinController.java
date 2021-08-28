package com.autotradeserver.controller;

import com.autotradeserver.config.Configs;
import com.autotradeserver.exceptions.CompletableFutureException;
import com.autotradeserver.exceptions.CompletableFutureInterruptException;
import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/coin")
@RequiredArgsConstructor
public class CoinController {

    private final Configs config;
    private final StreamData streamData;

    @GetMapping("")
    public Map<String, String> healthCheck(){
        return Map.of(
                "Health", HttpStatus.OK.name()
        );
    }

    @GetMapping("/trade")
    public String streamCoinInfo()
            throws CompletableFutureException, CompletableFutureInterruptException, SocketConnectException, SocketCreateException {
        String url = config.getValue("coin.url");
        streamData.createSocket(url);
        JSONObject json = streamData.returnCurrentMsg("[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-ETH\"],\"isOnlyRealtime\":1}]");
        while(true){
            System.out.println("Real Json : " + json);
        }
    }
}
