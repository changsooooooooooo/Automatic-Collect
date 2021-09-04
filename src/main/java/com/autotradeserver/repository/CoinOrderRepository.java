package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinOrderbookDTO;
import com.autotradeserver.dto.coinsector.CoinTradeDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinOrderRepository extends ElasticsearchRepository<CoinOrderbookDTO, String> {
}
