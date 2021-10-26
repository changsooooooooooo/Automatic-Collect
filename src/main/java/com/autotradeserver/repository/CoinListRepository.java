package com.autotradeserver.repository;

import com.autotradeserver.data.entity.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinListRepository extends JpaRepository<CoinEntity, String> {
}
