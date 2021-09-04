package com.autotradeserver.dto.coinsector;

import com.autotradeserver.repository.CoinTradeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class CoinDataDTOTest {

    @Resource
    private CoinTradeRepository coinRepository;

    @Test
    void insertToElastic() throws JsonProcessingException {
        String value = "{\"market_state_for_ios\":null,\"trade_volume\":0.0044,\"code\":\"KRW-BTC\",\"delisting_date\":null,\"trade_time\":\"105119\",\"signed_change_rate\":0.017863704,\"trade_timestamp\":1630666279000,\"acc_trade_price_24h\":491105981813.01854,\"signed_change_price\":1021000,\"acc_trade_volume\":5642.68674824,\"type\":\"ticker\",\"lowest_52_week_price\":11860000,\"acc_ask_volume\":2866.81667073,\"market_warning\":\"NONE\",\"trade_status\":null,\"stream_type\":\"SNAPSHOT\",\"prev_closing_price\":57155000,\"low_price\":56500000,\"acc_bid_volume\":2775.87007751,\"timestamp\":1630666279716,\"opening_price\":57155000,\"trade_price\":58176000,\"market_state\":\"ACTIVE\",\"is_trading_suspended\":false,\"change\":\"RISE\",\"lowest_52_week_date\":\"2020-09-07\",\"acc_trade_volume_24h\":8523.07801613,\"high_price\":58500000,\"trade_date\":\"20210903\",\"change_rate\":0.017863704,\"highest_52_week_date\":\"2021-04-14\",\"highest_52_week_price\":81994000,\"acc_trade_price\":324696396788.70275,\"change_price\":1021000,\"ask_bid\":\"ASK\"}";

        JSONObject json = new JSONObject(value);
        Long time = json.getLong("timestamp");
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ss.SSS");
        json.put("current_time", format1.format(new Date(time)));
        ObjectMapper mapper = new ObjectMapper();

        CoinTradeDTO coin = mapper.readValue(json.toString(), CoinTradeDTO.class);
        coinRepository.save(coin);

    }

}
