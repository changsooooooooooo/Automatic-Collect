package com.autotradeserver.service.websockets;

import com.amazonaws.services.s3.AmazonS3;
import com.autotradeserver.config.AWSS3Config;
import com.autotradeserver.repository.CoinThemeRepository;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class CoinWebSocketAdapter extends WebSocketAdapter {

    @Value("${coin.base-cutoff}")
    private int cutOff;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    private CompletableFuture<JSONObject> completableFuture;

    private final AWSS3Config awss3Config;
    private final CoinThemeRepository coinDBRepository;

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) {
        String text = new String(binary);
        JSONObject json = new JSONObject(text);
        accumulateToS3(json);
        log.info("Real Json : {}", json);
        completableFuture.complete(json);
    }

    public void accumulateToS3(final JSONObject item){
        AmazonS3 s3 = awss3Config.amazonS3();
        int now = LocalDateTime
                .now()
                .getHour();
        int quarter = now/cutOff;
        String cat = coinDBRepository.findCoinCategory(item.getString("cd"));
        String bucketPath = cat
                +"/"
                +item.getString("cd")
                + "/"
                +item.getString("td")
                +"/"
                +quarter
                +"/"
                +item.getLong("sid")
                +".json";
        s3.putObject(bucketName, bucketPath, item.toString());
    }

    public JSONObject returnCurrentJson() {
        completableFuture = new CompletableFuture<>();
        return completableFuture.join();
    }
}
