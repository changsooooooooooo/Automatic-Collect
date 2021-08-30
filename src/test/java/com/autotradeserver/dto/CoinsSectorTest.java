package com.autotradeserver.dto;

import com.autotradeserver.dto.coinsector.CoinsSector;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class CoinsSectorTest {

    @RequiredArgsConstructor
    static class Temp{

        @JsonProperty("china")
        private List<String> china;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    private CoinsSector coinsSector;

    @Test
    @DisplayName("Make Json Test")
    void makeJsonArray() throws IOException {

        String testValue = "{\"china\" : [\"KRW-NEO\", \"KRW-TRX\", \"KRW-ONT\", \"KRW-ELF\", \"KRW-VET\"]}";
        Temp temp = mapper.readValue(testValue, Temp.class);
        System.out.println(temp.china);
    }

    @Test
    @DisplayName("From Json List Test")
    void readValueMapperTest() throws IOException {
        coinsSector = mapper.readValue(new File("./coin_list.json"), CoinsSector.class);
        System.out.println(coinsSector.getEthereum());
    }
}
