package com.xeanco.xeanco.controller;


import com.xeanco.xeanco.model.Product;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("")
@CrossOrigin
@SpringBootApplication
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("admin/product")
    public ResponseEntity<?> saveProduct(@Valid @RequestParam MultipartFile file, Product product, BindingResult result){
            ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
            if(errorMap != null){
                return errorMap;
            }
            Product prod1 = productService.saveOrUpdateProduct(file, product);
            return new ResponseEntity<Product> (prod1, HttpStatus.CREATED);
    }

    @GetMapping("api/product/{productIdentifier}")
    public ResponseEntity<?> getProductById(@PathVariable String productIdentifier){
        Product product2 = productService.findByProductIdentifier(productIdentifier);
        return new ResponseEntity<Product>(product2, HttpStatus.OK);
    }

    @GetMapping("api/product/all")
    public Iterable<Product> getAllProduct(){
        return productService.findAllProduct();
    }

    @DeleteMapping("/{productIdentifier}")
    public ResponseEntity<?> deleteProductById(@PathVariable String productIdentifier){
        productService.deleteProductByIdentifier(productIdentifier);
        return  new ResponseEntity<String>("User with ID: " + productIdentifier + " was deleted", HttpStatus.OK);
    }

    @PutMapping("admin/product")
    public ResponseEntity<?> updateProduct(@RequestParam MultipartFile file, Product product,  BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Product prod1 = productService.updateProduct(file, product);
        return new ResponseEntity<Product> (prod1, HttpStatus.CREATED);
    }
}
