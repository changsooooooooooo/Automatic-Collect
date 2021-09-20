package com.autotradeserver.dto.coinsector;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "coin_trade")
public class CoinTradeDTO {

    @Id
    @Timestamp
    @JsonProperty("trade_timestamp")
    @Field(type=FieldType.Long, name="trade_timestamp")
    private Date ttms;

    @Field(type=FieldType.Text, name="code")
    @JsonProperty("code")
    private String code;

    @JsonProperty("timestamp")
    @Field(type=FieldType.Long, name="timestamp")
    private Long timestamp;

    @JsonProperty("market_state_for_ios")
    @Field(type=FieldType.Text, name="market_state_for_ios")
    private String marketState;

    @JsonProperty("trade_volume")
    @Field(type=FieldType.Double, name="trade_volume")
    private Double tradeVoulme;

    @JsonProperty("delisting_date")
    @Field(type=FieldType.Date, name="delisting_date")
    private Date dd;

    @JsonProperty("trade_time")
    @Field(type=FieldType.Text, name="trade_time")
    private String tradeTime;

    @JsonProperty("signed_change_rate")
    @Field(type=FieldType.Double, name="signed_change_rate")
    private Double scr;

    @JsonProperty("acc_trade_price_24h")
    @Field(type=FieldType.Double, name="acc_trade_price_24h")
    private Double atp24h;

    @JsonProperty("signed_change_price")
    @Field(type=FieldType.Double, name="signed_change_price")
    private Double scp;

    @JsonProperty("acc_trade_volume")
    @Field(type=FieldType.Double, name="acc_trade_volume")
    private Double atv;

    @JsonProperty("type")
    @Field(type=FieldType.Text, name="type")
    private String type;

    @JsonProperty("lowest_52_week_price")
    @Field(type=FieldType.Double, name="lowest_52_week_price")
    private Double l52wp;

    @JsonProperty("acc_ask_volume")
    @Field(type=FieldType.Double, name="acc_ask_volume")
    private Double aav;

    @JsonProperty("market_warning")
    @Field(type=FieldType.Text, name="market_warning")
    private String mw;

    @JsonProperty("trade_status")
    @Field(type=FieldType.Text, name="trade_status")
    private String ts;

    @JsonProperty("stream_type")
    @Field(type=FieldType.Text, name="stream_type")
    private String st;

    @JsonProperty("prev_closing_price")
    @Field(type=FieldType.Double, name="prev_closing_price")
    private Double pcp;

    @JsonProperty("low_price")
    @Field(type=FieldType.Double, name="low_price")
    private Double lp;

    @JsonProperty("acc_bid_volume")
    @Field(type=FieldType.Double, name="acc_bid_volume")
    private Double abv;

    @JsonProperty("opening_price")
    @Field(type=FieldType.Double, name="opening_price")
    private Double op;

    @JsonProperty("trade_price")
    @Field(type=FieldType.Double, name="trade_price")
    private Double tp;

    @JsonProperty("market_state")
    @Field(type=FieldType.Text, name="market_state")
    private String ms;

    @JsonProperty("is_trading_suspended")
    @Field(type=FieldType.Boolean, name="is_trading_suspended")
    private Boolean its;

    @JsonProperty("change")
    @Field(type=FieldType.Text, name="change")
    private String change;

    @JsonProperty("lowest_52_week_date")
    @Field(type=FieldType.Text, name="lowest_52_week_date")
    private String l52wdt;

    @JsonProperty("acc_trade_volume_24h")
    @Field(type=FieldType.Double, name="acc_trade_volume_24h")
    private Double atv24h;

    @JsonProperty("high_price")
    @Field(type=FieldType.Double, name="high_price")
    private Double hp;

    @JsonProperty("trade_date")
    @Field(type=FieldType.Text, name="trade_date")
    private String td;

    @JsonProperty("change_rate")
    @Field(type=FieldType.Double, name="change_rate")
    private Double cr;

    @JsonProperty("highest_52_week_date")
    @Field(type=FieldType.Text, name="highest_52_week_date")
    private String h52wdt;

    @JsonProperty("highest_52_week_price")
    @Field(type=FieldType.Double, name="highest_52_week_price")
    private Double h52wp;

    @JsonProperty("acc_trade_price")
    @Field(type=FieldType.Double, name="acc_trade_price")
    private Double atp;

    @JsonProperty("change_price")
    @Field(type=FieldType.Double, name="change_price")
    private Double cp;

    @JsonProperty("ask_bid")
    @Field(type=FieldType.Text, name="ask_bid")
    private String ab;

    @Timestamp
    @JsonProperty("current_time")
    @Field(type=FieldType.Date, name="current_time")
    private Date currentTime;
}
