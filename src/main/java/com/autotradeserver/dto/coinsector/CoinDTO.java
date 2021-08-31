package com.autotradeserver.dto.coinsector;

import com.autotradeserver.config.Configs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
@RequiredArgsConstructor
public class CoinDTO {

    private CoinsSector coinsSector;
    private final Configs configs;
    private final ObjectMapper objectMapper;

    public void makeJsonObj() throws IOException {
        String filePath = configs.getValue("coin.sector.path");
        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        coinsSector = objectMapper.readValue(bis, CoinsSector.class);
    }
}
