package com.xeanco.xeanco.controller;


import com.xeanco.xeanco.model.About;
import com.xeanco.xeanco.service.AboutService;
import com.xeanco.xeanco.service.ErrorHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
@SpringBootApplication
public class AboutController {
    @Autowired
    AboutService aboutService;

    @Autowired
    private ErrorHandlerService errorHandlerService;

    @PostMapping("/admin/about")
    public ResponseEntity<?> saveAbout(@Valid @RequestBody About about, BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        About abt1 = aboutService.saveOrUpdate(about);
        return new ResponseEntity<About>(abt1, HttpStatus.CREATED);
    }

    @GetMapping("api/about/{id}")
    public ResponseEntity<?> getAboutById(@PathVariable long id){
        About abt2 = aboutService.findById(id);
        return new ResponseEntity<About>(abt2, HttpStatus.OK);
    }

    @GetMapping("api/about/all")
    public List<About> getAllAbouts(){
        return aboutService.findAllItem();
    }

    @DeleteMapping("admin/about/{id}")
    public ResponseEntity<?> deleteAbout(@PathVariable long id){
        aboutService.deleteById(id);
        return new ResponseEntity<String>("User with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
