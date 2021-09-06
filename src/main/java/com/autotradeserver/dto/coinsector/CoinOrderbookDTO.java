package com.autotradeserver.dto.coinsector;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "coin_orderbook")
public class CoinOrderbookDTO {

    @Id
    @JsonProperty("timestamp")
    @Timestamp
    @Field(type= FieldType.Long, name="timestamp")
    private Date tms;

    @JsonProperty("code")
    @Field(type=FieldType.Text, name="code")
    private String code;

    @JsonProperty("stream_type")
    @Field(type=FieldType.Text, name="stream_type")
    private String streamType;

    @JsonProperty("type")
    @Field(type=FieldType.Text, name="type")
    private String type;

    @JsonProperty("total_ask_size")
    @Field(type=FieldType.Double, name="total_ask_size")
    private Double tas;

    @JsonProperty("total_bid_size")
    @Field(type=FieldType.Double, name="total_bid_size")
    private Double tbs;

    @JsonProperty("orderbook_units")
    @Field(type=FieldType.Object, name="orderbook_units")
    private List<CoinOrders> obu;

    @Timestamp
    @JsonProperty("current_time")
    @Field(type=FieldType.Date, name="current_time")
    private Date currentTime;
}
