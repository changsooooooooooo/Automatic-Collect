package com.autotradeserver.dto.coinsector;

import com.autotradeserver.config.Configs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CoinSendDTO {

    private CoinsSector coinsSector;
    private JSONObject message;
    private JSONObject messageBody;
    private final Configs configs;
    private final ObjectMapper objectMapper;

    public void makeJsonObj() throws IOException {
        String path = configs.getValue("coin.sector.path");
        FileInputStream fr = new FileInputStream(path);
        BufferedInputStream reader = new BufferedInputStream(fr);
        coinsSector = objectMapper.readValue(reader, CoinsSector.class);
    }

    public void makeJsonMsg(){
        message = new JSONObject(coinsSector.getMesasgeHead());
    }

    public void makeJsonMsgBody(String category){
        List<String> coins = coinsSector.getCoin();
        messageBody = new JSONObject(coinsSector.getMessageBody());
        messageBody.put("type", category);
        messageBody.put("codes", coins);
    }

    public String returnSendMsg(String category){
        makeJsonMsg();
        makeJsonMsgBody(category);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(message);
        jsonArray.put(messageBody);
        return jsonArray.toString();
    }
}
