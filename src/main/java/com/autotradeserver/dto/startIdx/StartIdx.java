package com.autotradeserver.dto.startIdx;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartIdx {

    private int strIdx;

    public int curIdx(){
        return strIdx;
    }

    public void changeStartIdx(final int movePos){
        strIdx = movePos;
    }
}
