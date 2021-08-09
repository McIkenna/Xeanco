package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IProductService {
    Product saveOrUpdateProduct(MultipartFile file, Product product);
    Product findByProductIdentifier(String productIdentifier);
}
