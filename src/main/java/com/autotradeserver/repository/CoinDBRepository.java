package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinThemeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinDBRepository extends JpaRepository<CoinThemeDTO, String> {

    @Query(value = "select coin_name from coins where coin_category= :theme", nativeQuery = true)
    List<String> findCoinCadidatesByTheme(@Param(value="theme") String theme);
}
