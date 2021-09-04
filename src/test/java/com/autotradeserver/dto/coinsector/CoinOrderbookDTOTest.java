package com.autotradeserver.dto.coinsector;

import com.autotradeserver.repository.CoinOrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinOrderbookDTOTest {

    @Autowired
    private CoinOrderRepository coinOrderRepository;

    @Test
    void makeOrderBookTest() throws JsonProcessingException {
        String value = "{\"code\":\"KRW-BTC\",\"total_bid_size\":3.08693392,\"stream_type\":\"REALTIME\",\"type\":\"orderbook\",\"orderbook_units\":[{\"ask_size\":2.01134124,\"bid_size\":0.01187843,\"bid_price\":5.7209E+7,\"ask_price\":5.7258E+7},{\"ask_size\":2.64478027,\"bid_size\":0.09517571,\"bid_price\":5.7208E+7,\"ask_price\":5.7259E+7},{\"ask_size\":0.51362173,\"bid_size\":1.04265564,\"bid_price\":5.7206E+7,\"ask_price\":5.7262E+7},{\"ask_size\":0.14127395,\"bid_size\":0.80392148,\"bid_price\":5.7205E+7,\"ask_price\":5.7265E+7},{\"ask_size\":0.01192779,\"bid_size\":0.2111685,\"bid_price\":5.7204E+7,\"ask_price\":5.7267E+7},{\"ask_size\":0.0078091,\"bid_size\":0.01045651,\"bid_price\":5.7201E+7,\"ask_price\":5.727E+7},{\"ask_size\":1.61730273,\"bid_size\":0.55242188,\"bid_price\":5.72E+7,\"ask_price\":5.7276E+7},{\"ask_size\":0.17128594,\"bid_size\":0.19683095,\"bid_price\":5.7199E+7,\"ask_price\":5.7278E+7},{\"ask_size\":0.14249694,\"bid_size\":0.050142,\"bid_price\":5.7198E+7,\"ask_price\":5.728E+7},{\"ask_size\":0.1389943,\"bid_size\":0.00831855,\"bid_price\":5.7196E+7,\"ask_price\":5.7281E+7},{\"ask_size\":0.45150185,\"bid_size\":0.00226231,\"bid_price\":5.7195E+7,\"ask_price\":5.73E+7},{\"ask_size\":0.1935,\"bid_size\":0.00018484,\"bid_price\":5.7192E+7,\"ask_price\":5.7301E+7},{\"ask_size\":0.00128659,\"bid_size\":0.04422089,\"bid_price\":5.7189E+7,\"ask_price\":5.7303E+7},{\"ask_size\":0.24911696,\"bid_size\":0.00375968,\"bid_price\":5.7186E+7,\"ask_price\":5.7304E+7},{\"ask_size\":0.07329629,\"bid_size\":0.05353655,\"bid_price\":5.7185E+7,\"ask_price\":5.7307E+7}],\"total_ask_size\":8.36953568,\"timestamp\":1630721238115}";

        ObjectMapper mapper = new ObjectMapper();

        JSONObject json = new JSONObject(value);

        Long time = json.getLong("timestamp");
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ss.SSS");
        json.put("current_time", format1.format(new Date(time)));
        CoinOrderbookDTO coinOrderbookDTO = mapper.readValue(json.toString(), CoinOrderbookDTO.class);

        coinOrderRepository.save(coinOrderbookDTO);

    }

}
