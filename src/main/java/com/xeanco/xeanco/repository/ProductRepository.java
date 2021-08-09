package com.xeanco.xeanco.repository;


import com.xeanco.xeanco.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductIdentifier(String productIdentifier);
    Iterable<Product> findAll();
}