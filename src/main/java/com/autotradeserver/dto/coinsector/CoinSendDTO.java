package com.autotradeserver.dto.coinsector;

import com.autotradeserver.config.Configs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class CoinSendDTO {

    private CoinsSector coinsSector;
    private final Configs configs;
    private final ObjectMapper objectMapper;

    public void makeJsonObj() throws IOException {
        String path = configs.getValue("coin.sector.path");
        FileInputStream fr = new FileInputStream(path);
        BufferedInputStream reader = new BufferedInputStream(fr);
        coinsSector = objectMapper.readValue(reader, CoinsSector.class);
    }
}
