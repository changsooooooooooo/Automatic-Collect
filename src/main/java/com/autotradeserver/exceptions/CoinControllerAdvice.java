package com.autotradeserver.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice(annotations = RestController.class)
public class CoinControllerAdvice {

    @ExceptionHandler({SocketConnectException.class, SocketCreateException.class})
    public ResponseEntity<Object> badConnection(final Exception e) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap = returnErrorJson(e);
        log.info("Error Map : {}", errorMap);
        return ResponseEntity.badRequest().body(errorMap);
    }

    public Map<String, Object> returnErrorJson(Exception e){
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("Cause", e.getClass());
        errorMap.put("Message", e.getMessage());
        return errorMap;
    }

}
