package com.autotradeserver.dto.startIdx;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class StartIdx {

    private int strIdx;

    public int curIdx(){
        return strIdx;
    }

    public void changeStartIdx(final int movePos){
        strIdx = movePos;
        log.info("StartIdx : {}, Need To Move Pos : {}", strIdx, movePos);
    }
}
