package com.autotradeserver.repository;

import com.autotradeserver.data.entity.CoinThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinThemeRepository extends JpaRepository<CoinThemeEntity, Long> {

    @Query(value = "select coin_name from coin_theme where coin_category= :theme", nativeQuery = true)
    List<String> findCoinCadidatesByTheme(@Param(value="theme") String theme);

    @Query(value = "select coin_category from coin_theme where coin_name= :name", nativeQuery = true)
    String findCoinCategory(@Param(value="name") String name);
}
