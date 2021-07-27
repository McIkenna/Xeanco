package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
        Feature findByFeatureIdentifier(String featureId);

        @Override
        Iterable<Feature> findAll();
}
