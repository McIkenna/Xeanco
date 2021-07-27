package com.xeanco.xeanco.controller;


import com.xeanco.xeanco.model.About;
import com.xeanco.xeanco.service.ErrorHandlerService;
import com.xeanco.xeanco.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/about")
@CrossOrigin
@SpringBootApplication
public class AboutController {
    @Autowired
    AboutService aboutService;

    @Autowired
    private ErrorHandlerService errorHandlerService;

    @PostMapping("")
    public ResponseEntity<?> saveAbout(@RequestBody About about, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        About abt1 = aboutService.saveOrUpdate(about);
        return new ResponseEntity<About>(abt1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAboutById(@PathVariable long id){
        About abt2 = aboutService.findById(id);
        return new ResponseEntity<About>(abt2, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<About> getAllAbouts(){
        return aboutService.findAllItem();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAbout(@PathVariable long id){
        aboutService.deleteById(id);
        return new ResponseEntity<String>("User with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
