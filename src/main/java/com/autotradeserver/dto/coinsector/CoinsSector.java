package com.autotradeserver.dto.coinsector;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CoinsSector {

    @JsonProperty("coin")
    private List<String> coin;

    @JsonProperty("message_ticket")
    private String mesasgeHead;

    @JsonProperty("message_body")
    private String messageBody;
}
