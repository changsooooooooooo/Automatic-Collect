package com.autotradeserver.repository;

import com.autotradeserver.data.entity.CoinEntity;
import com.autotradeserver.data.entity.CoinThemeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CoinListRepositoryTest {

    @Autowired
    private CoinListRepository coinListRepository;

    @Autowired
    private CoinThemeRepository coinThemeRepository;

    private CoinEntity coinEntity;
    private CoinThemeEntity coinThemeEntity;

    @BeforeEach
    void setUp(){
        coinEntity = new CoinEntity().builder()
                .coinName("KRW-BTC")
                .build();

        coinThemeEntity = new CoinThemeEntity().builder()
                .coinDAO(coinEntity)
                .coinCategory("bitcoin")
                .build();
    }

    @Test
    @DisplayName("Update Test")
    void showUpdate(){
        coinListRepository.save(coinEntity);
        coinThemeRepository.save(coinThemeEntity);
    }

}
