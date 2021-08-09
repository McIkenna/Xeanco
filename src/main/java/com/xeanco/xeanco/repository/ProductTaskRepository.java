package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.ProductTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTaskRepository extends CrudRepository<ProductTask, Long> {
    ProductTask findByProductIdentifier(String productIdentifier);
}
