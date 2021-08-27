package com.autotradeserver.dto;

import com.autotradeserver.exceptions.SocketConnectException;
import com.autotradeserver.exceptions.SocketCreateException;
import com.autotradeserver.service.domain.StreamData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.concurrent.Flow.Publisher;

@Service
@Getter
@RequiredArgsConstructor
public class CoinDataPublisher{

    private static StreamData streamData;
    //싱글톤으로 봤을 때 static 으로 소켓 하나를 통한 메세징이 맞을듯.. 객체 하나로 하는게 맞지 않나..??

    public void makeSubscriptionObj(String url) throws SocketConnectException, SocketCreateException {
        streamData.createSocket(url);
    }

    public void subscribe(String msg){
        publishMsg(msg).subscribe(
                new CoinDataSubscriber()
        );
    }

    private static Publisher<JSONObject> publishMsg(String msg){
        return subscriber -> subscriber.onSubscribe(
                new CoinDataSubscription(msg, streamData, subscriber)
        );
    }

    public StreamData returnStreamData(){
        return streamData;
    }

}
