package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.Clients;
import com.xeanco.xeanco.response.Response;
import com.xeanco.xeanco.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/client")
@CrossOrigin
@SpringBootApplication
public class ClientController {

    @Autowired
    ClientService clientService;

    public Response save(@RequestParam MultipartFile file, Clients clients){
        Clients clients1 = clientService.save(file, clients);
        Response response = new Response();
        if(clients.getId() != null){
            response.setMessage("Intro Save Successfully");
            return response;
        }
        response.setMessage("Intro was not saved");
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Clients client1 = clientService.findClientsById(id);
        return new ResponseEntity<Clients>(client1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(long id){
        clientService.deleteById(id);
        return  new ResponseEntity<String>("Clients with ID: " + id + " was deleted", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Clients> getAllClients(){
        return clientService.findAllClients();
    }
}
