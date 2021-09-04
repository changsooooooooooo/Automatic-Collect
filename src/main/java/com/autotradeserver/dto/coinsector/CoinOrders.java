package com.autotradeserver.dto.coinsector;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class CoinOrders {

    @JsonProperty("ask_price")
    @Field(type= FieldType.Double, name = "ask_price")
    private Double ap;

    @JsonProperty("bid_price")
    @Field(type= FieldType.Double, name = "bid_price")
    private Double bp;

    @JsonProperty("ask_size")
    @Field(type= FieldType.Double, name = "ask_size")
    private Double as;

    @JsonProperty("bid_size")
    @Field(type= FieldType.Double, name = "ask_size")
    private Double bs;
}
