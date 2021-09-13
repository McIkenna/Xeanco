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

import javax.validation.Valid;

@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("")
public class FeatureController {

    @Autowired
    FeatureService featureService;
    @Autowired
    ErrorHandlerService errorHandlerService;

    @PostMapping("admin/feature")
    public ResponseEntity<?> saveFeature(@Valid @RequestParam MultipartFile file, Feature feature, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Feature feature1 = featureService.save(file, feature);
        return new ResponseEntity<Feature>(feature1, HttpStatus.CREATED);
    }

    @PutMapping("admin/feature")
    public ResponseEntity<?> updateFeature(@Valid @RequestParam MultipartFile file, Feature feature, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Feature feature1 = featureService.update(file, feature);
        return new ResponseEntity<Feature>(feature1, HttpStatus.CREATED);
    }

    @GetMapping("api/feature/{id}")
    public ResponseEntity<?> getFeatureByIdentifier(@PathVariable String id){
        Feature feature2 = featureService.findFeatureByIdentifier(id);
        return new ResponseEntity<Feature>(feature2, HttpStatus.OK);
    }

    @GetMapping("api/feature/all")
    public Iterable<Feature> getAllFeatures(){
        return featureService.findAllFeature();
    }

    @DeleteMapping("admin/feature/{id}")
    public ResponseEntity<?> deleteAbout(@PathVariable String id){
        featureService.deleteFeatureByIdentifier(id);
        return new ResponseEntity<String>("User with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
