package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinTradeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinTradeDBRepository extends JpaRepository<CoinTradeDTO, String> {
}
