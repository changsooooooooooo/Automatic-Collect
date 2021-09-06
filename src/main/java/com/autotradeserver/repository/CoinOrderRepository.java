package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinOrderbookDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinOrderRepository extends ElasticsearchRepository<CoinOrderbookDTO, String> {
}
