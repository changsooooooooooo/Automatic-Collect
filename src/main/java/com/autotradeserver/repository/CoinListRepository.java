package com.autotradeserver.repository;

import com.autotradeserver.data.dao.CoinDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinListRepository extends JpaRepository<CoinDAO, String> {
}
