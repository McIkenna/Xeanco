package com.xeanco.xeanco.controller;

import com.xeanco.xeanco.model.Client;
import com.xeanco.xeanco.response.Response;
import com.xeanco.xeanco.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin
@SpringBootApplication
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("admin/client")
    public Response save(@Valid @RequestParam MultipartFile file, Client client){
        Client client1 = clientService.save(file, client);
        Response response = new Response();
        if(client1 != null){
            response.setMessage("Intro Save Successfully");
            return response;
        }
        response.setMessage("Intro was not saved");
        return response;
    }

    @GetMapping("api/client/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Client client1 = clientService.findClientsById(id);
        return new ResponseEntity<Client>(client1, HttpStatus.OK);
    }

    @DeleteMapping("admin/client/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        clientService.deleteById(id);
        return  new ResponseEntity<String>("Clients with ID: " + id + " was deleted", HttpStatus.OK);
    }

    @GetMapping("api/client/all")
    public List<Client> getAllClients(){
        return clientService.findAllClients();
    }
}
