package com.autotradeserver.repository;

import com.autotradeserver.dto.coinsector.CoinDataDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends ElasticsearchRepository<CoinDataDTO, String> {
}
