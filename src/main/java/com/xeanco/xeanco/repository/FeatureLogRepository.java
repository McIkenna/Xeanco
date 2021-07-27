package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.FeatureLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureLogRepository extends CrudRepository<FeatureLog, Long> {
    FeatureLog findByFeatureIdentifier(String Id);
}
