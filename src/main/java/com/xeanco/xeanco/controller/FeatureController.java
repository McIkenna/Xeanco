package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.Feature;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("api/feature")
public class FeatureController {

    @Autowired
    FeatureService featureService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("")
    public ResponseEntity<?> saveFeature(@RequestParam MultipartFile file, Feature feature, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Feature feature1 = featureService.saveOrUpdate(file, feature);
        return new ResponseEntity<Feature>(feature1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeatureById(@PathVariable String id){
        Feature feature2 = featureService.findFeatureById(id);
        return new ResponseEntity<Feature>(feature2, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Feature> getAllFeatures(){
        return featureService.findAllFeature();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAbout(@PathVariable String id){
        featureService.deleteFeatureById(id);
        return new ResponseEntity<String>("User with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
