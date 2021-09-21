package com.autotradeserver.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinDBRepositoryTest {

    @Autowired
    private CoinDBRepository coinDBRepository;

    @Test
    void testQuery(){
        List<String> candidate = coinDBRepository.findCoinCadidatesByTheme("korea");
        candidate.stream()
                .forEach(x-> System.out.println(x));
    }

}
