package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IClientService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Client;
import com.xeanco.xeanco.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Client save(MultipartFile file, Client client) {
        try{
            client.setClientImgName(file.getOriginalFilename());
            client.setClientImg(file.getBytes());
            return clientRepository.save(client);
        }catch(Exception e){
            throw new IntroException("ClientName '" + client.getClientName() + "Already exist");
        }
    }

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public Client findClientsById(long id){
        Client clients = clientRepository.findById(id);
        if(clients == null){
            throw new IntroException("Clients with Id: "+ id + " Does not exist");
        }
        return clients;
    }

    public void deleteById(long id){
        Client clients = clientRepository.findById(id);
        if(clients == null){
            throw new IntroException("Clients with Id: "+ id + " Does not exist");
        }
        clientRepository.delete(clients);
    }
}
