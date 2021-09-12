package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.FeatureTask;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.FeatureTaskService;
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
public class FeatureTaskController {

    @Autowired
    FeatureTaskService featureTaskService;
    @Autowired
    ErrorHandlerService errorHandlerService;
/*
    @PostMapping("/{featureLogId}")
    public ResponseEntity<?> saveFeature(@RequestParam MultipartFile file, FeatureTask featureTask, @PathVariable String featureLogId, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        FeatureTask featureTask1 = featureTaskService.save(file, featureLogId, featureTask);
        return new ResponseEntity<FeatureTask>(featureTask1, HttpStatus.CREATED);
    }
*/
    @PutMapping("admin/featureTask/update")
    public ResponseEntity<?> saveFeature(@Valid @RequestParam MultipartFile file, FeatureTask featureTask, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        FeatureTask featureTask1 = featureTaskService.update(file, featureTask);
        return new ResponseEntity<FeatureTask>(featureTask1, HttpStatus.CREATED);
    }
    @GetMapping("api/task/{id}")
    public ResponseEntity<?> getFeatureById(@PathVariable String id){
        FeatureTask task2 = featureTaskService.getFeatureTask(id);
        return new ResponseEntity<FeatureTask>(task2, HttpStatus.OK);
    }


}
