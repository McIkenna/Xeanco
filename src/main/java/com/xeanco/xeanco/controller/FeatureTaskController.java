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

@RestController
@RequestMapping("api/task")
@CrossOrigin
@SpringBootApplication
public class FeatureTaskController {

    @Autowired
    FeatureTaskService featureTaskService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("/{featureId}")
    public ResponseEntity<?> saveFeature(@RequestParam MultipartFile file, FeatureTask featureTask, @PathVariable String featureId,  BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        FeatureTask featureTask1 = featureTaskService.saveOrUpdate(file, featureId, featureTask);
        return new ResponseEntity<FeatureTask>(featureTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeatureById(@PathVariable String id){
        FeatureTask task2 = featureTaskService.getFeatureTask(id);
        return new ResponseEntity<FeatureTask>(task2, HttpStatus.OK);
    }


}
