package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.ProductLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLogRepository extends CrudRepository<ProductLog, Long> {
    ProductLog findByProductIdentifier(String productIdentifier);
}
