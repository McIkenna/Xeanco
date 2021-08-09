package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.ProductTask;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.ProductTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/productTask")
@CrossOrigin
@SpringBootApplication
public class ProductTaskController {

    @Autowired
    ProductTaskService productTaskService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("/{productId}")
    public ResponseEntity<?> saveProduct(@RequestParam MultipartFile file, ProductTask productTask, @PathVariable String productId, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        ProductTask productTask1 = productTaskService.saveOrUpdate(file, productId, productTask);
        return new ResponseEntity<ProductTask>(productTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        ProductTask productTask2 = productTaskService.getProductById(id);
        return new ResponseEntity<ProductTask>(productTask2, HttpStatus.OK);
    }
}
