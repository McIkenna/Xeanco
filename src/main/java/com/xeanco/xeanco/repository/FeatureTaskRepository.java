package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.FeatureTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureTaskRepository extends CrudRepository<FeatureTask, Long>{
    FeatureTask findByFeatureIdentifier(String Id);
}
