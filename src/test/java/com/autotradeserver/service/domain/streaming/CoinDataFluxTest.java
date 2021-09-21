package com.autotradeserver.service.domain.streaming;

import com.autotradeserver.dto.coinsector.CoinSendDTO;
import com.autotradeserver.repository.CoinDBRepository;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@SpringBootTest
class CoinDataFluxTest {

    @Autowired
    private CoinDataPublisher coinDataPublisher;

    @Autowired
    private CoinSendDTO coinSendDTO;

    @Test
    void fluxFromTest(){
        List<JSONArray> msgJsonList = coinSendDTO.makeMsgList("korea");
        msgJsonList.stream()
                .forEach(x-> System.out.println(x.toString()));
        Flux<JSONArray> fluxMsg = Flux.fromIterable(msgJsonList);
        Duration period = Duration.ofMillis(1000/msgJsonList.size());
        fluxMsg.subscribe(x-> System.out.println(x));
    }

}
