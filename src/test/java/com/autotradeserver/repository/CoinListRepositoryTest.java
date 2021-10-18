package com.autotradeserver.repository;

import com.autotradeserver.data.dao.CoinDAO;
import com.autotradeserver.data.dao.CoinThemeDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CoinListRepositoryTest {

    @Autowired
    private CoinListRepository coinListRepository;

    @Autowired
    private CoinThemeRepository coinThemeRepository;

    private CoinDAO coinDAO;
    private CoinThemeDAO coinThemeDAO;

    @BeforeEach
    void setUp(){
        coinDAO = new CoinDAO().builder()
                .coinName("KRW-BTC")
                .build();

        coinThemeDAO = new CoinThemeDAO().builder()
                .coinDAO(coinDAO)
                .coinCategory("bitcoin")
                .build();
    }

    @Test
    @DisplayName("Update Test")
    void showUpdate(){
        coinListRepository.save(coinDAO);
        coinThemeRepository.save(coinThemeDAO);
    }

}
