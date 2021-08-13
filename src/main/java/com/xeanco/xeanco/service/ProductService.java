package com.xeanco.xeanco.service;


import com.xeanco.xeanco.IService.IProductService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Product;
import com.xeanco.xeanco.model.ProductLog;
import com.xeanco.xeanco.repository.ProductLogRepository;
import com.xeanco.xeanco.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLogRepository productLogRepository;


    @Override
    public Product saveOrUpdateProduct(MultipartFile file, Product product) {
        UUID ranId = UUID.randomUUID();
       String productImgName = file.getOriginalFilename();
       String productName = product.getProductName();
       String productSummary = product.getProductSummary();
        String productImgType = file.getContentType();
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(product.getProductImgName())
                .toUriString();
        String productDownloadUrl = product.setProductDownloadUrl(downloadUrl);
        try{
            Product product1 = new Product(productName, productSummary, file.getBytes(), productImgName, productImgType, productDownloadUrl);
            product1.setProductIdentifier(ranId.toString());
            if(product1.getId() == null){
                ProductLog productLog = new ProductLog();
                product1.setProductLog(productLog);
                productLog.setProduct(product1);
                productLog.setProductIdentifier(product1.getProductIdentifier());
            }if(product1.getId() != null){
                product1.setProductLog(productLogRepository.findByProductIdentifier(product1.getProductIdentifier()));
            }
            return productRepository.save(product1);

        }catch(Exception e){
          throw new  IntroException(e.getLocalizedMessage());
        }
    }


    public Product findByProductIdentifier(String productIdentifier) {
        Product product1 =  productRepository.findByProductIdentifier(productIdentifier.toUpperCase());
       if(product1 == null){
            throw new IntroException("Product with ID: " + productIdentifier + " Does not exist");
        }
         return product1;
    }

    public Iterable<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public void deleteProductByIdentifier(String productIdentifier){
        Product prod = productRepository.findByProductIdentifier(productIdentifier);
        if(prod == null ){
            throw new IntroException("Product with ID: " + productIdentifier + " Does not exist");
        }
        productRepository.delete(prod);
    }


}
