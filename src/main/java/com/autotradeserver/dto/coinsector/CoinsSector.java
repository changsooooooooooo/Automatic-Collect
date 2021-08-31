package com.autotradeserver.dto.coinsector;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CoinsSector {

    @JsonProperty("bitcoin")
    private List<String> bitcoin;

    @JsonProperty("ethereum")
    private List<String> ethereum;

    @JsonProperty("ripple")
    private List<String> ripple;

    @JsonProperty("defi")
    private List<String> defi;

    @JsonProperty("did")
    private List<String> did;

    @JsonProperty("nft")
    private List<String> nft;

    @JsonProperty("platform")
    private List<String> platform;

    @JsonProperty("utility")
    private List<String> utility;

    @JsonProperty("stable")
    private List<String> stable;

    @JsonProperty("payment")
    private List<String> payment;

    @JsonProperty("korea")
    private List<String> korea;

    @JsonProperty("china")
    private List<String> china;
}
