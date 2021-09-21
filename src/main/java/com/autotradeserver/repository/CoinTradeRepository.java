package com.autotradeserver.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinTradeRepository extends ElasticsearchRepository<CoinTradeDTO, String> {
}
