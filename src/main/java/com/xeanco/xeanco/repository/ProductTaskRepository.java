package com.xeanco.xeanco.repository;

import com.xeanco.xeanco.model.ProductTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTaskRepository extends CrudRepository<ProductTask, Long> {
    List<ProductTask> findByProductIdentifier(String productIdentifier);

    ProductTask findByProductSequence(String sequence);
}
