package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.Extras;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@SpringBootApplication
@CrossOrigin
@RequestMapping("api/extra")
public class ExtraController {
    @Autowired
    ErrorHandlerService errorHandlerService;
    @Autowired
    ExtraService extraService;
    @PostMapping("")
    public ResponseEntity<?> saveExtras(@RequestParam MultipartFile[] files, Extras extra, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Extras extra1 = extraService.save(files, extra);
        return new ResponseEntity<Extras>(extra1, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExtrasById(@PathVariable long id){
        Extras extra = extraService.findExtraById(id);
        return new ResponseEntity<Extras>(extra, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Extras> getAllExtras(){
        return extraService.findAllExtra();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExtra(@PathVariable long id){
        extraService.deleteById(id);
        return new ResponseEntity<String>("Extra with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
