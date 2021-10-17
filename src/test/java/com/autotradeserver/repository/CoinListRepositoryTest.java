package com.autotradeserver.repository;

import com.autotradeserver.data.dao.CoinDAO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CoinListRepositoryTest {

    private CoinDAO coinDAO;

    @BeforeEach
    void setUp(){
        coinDAO = new CoinDAO().builder()
                .coinName("KRW-ETH")
                .build();


    }

}
