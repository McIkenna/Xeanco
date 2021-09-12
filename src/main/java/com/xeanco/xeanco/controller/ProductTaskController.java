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

import javax.validation.Valid;

@RestController
@RequestMapping("")
@CrossOrigin
@SpringBootApplication
public class ProductTaskController {

    @Autowired
    ProductTaskService productTaskService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("admin/productTask/{productId}")
    public ResponseEntity<?> saveProduct(@Valid @RequestParam MultipartFile file, ProductTask productTask, @PathVariable String productId, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        ProductTask productTask1 = productTaskService.saveOrUpdate(file, productId, productTask);
        return new ResponseEntity<ProductTask>(productTask1, HttpStatus.CREATED);
    }

    @GetMapping("api/productTask/{productlog_id}")
    public Iterable<ProductTask> getProductBacklog(@PathVariable String productlog_id){

        return productTaskService.getProductById(productlog_id);

    }

    @GetMapping("api/productTask/{productlog_id}/{pt_id}")
    public ResponseEntity<?> getProductTask(@PathVariable String productlog_id, @PathVariable String pt_id){
        ProductTask productTask = productTaskService.findPTByProductSequence(productlog_id, pt_id);
        return new ResponseEntity<ProductTask>( productTask, HttpStatus.OK);
    }


    @PutMapping("admin/productTask/{productlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@RequestParam MultipartFile file, ProductTask productTask, BindingResult result,
                                               @PathVariable String productlog_id, @PathVariable String pt_id ){

        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if (errorMap != null) return errorMap;

        ProductTask updatedTask = productTaskService.updateByProductSequence(file, productTask,productlog_id,pt_id);

        return new ResponseEntity<ProductTask>(updatedTask,HttpStatus.OK);

    }


    @DeleteMapping("admin/productTask/{productlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProductTask(@PathVariable String productlog_id, @PathVariable String pt_id){
        productTaskService.deletePTByProductSequence(productlog_id, pt_id);
        return new ResponseEntity<String>("Product Task "+pt_id+" was deleted successfully", HttpStatus.OK);
    }

}
