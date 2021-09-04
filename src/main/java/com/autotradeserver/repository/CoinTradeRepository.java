package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinTradeDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinTradeRepository extends ElasticsearchRepository<CoinTradeDTO, String> {
}
