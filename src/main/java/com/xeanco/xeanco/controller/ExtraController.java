package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.Extra;
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
    public ResponseEntity<?> saveExtras(@RequestParam MultipartFile file1,
                                        @RequestParam MultipartFile file2,
                                        @RequestParam MultipartFile file3,
                                        @RequestParam MultipartFile file4,
                                        Extra extra,
                                        BindingResult result){
        ResponseEntity<?> errorMap = errorHandlerService.ErrorHandlerService(result);
        if(errorMap != null){
            return errorMap;
        }
        Extra extra1 = extraService.save(file1,file2, file3, file4, extra);
        return new ResponseEntity<Extra>(extra1, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExtrasById(@PathVariable long id){
        Extra extra = extraService.findExtraById(id);
        return new ResponseEntity<Extra>(extra, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Extra> getAllExtras(){
        return extraService.findAllExtra();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExtra(@PathVariable long id){
        extraService.deleteById(id);
        return new ResponseEntity<String>("Extra with ID: " + id + " was deleted", HttpStatus.OK);
    }
}
