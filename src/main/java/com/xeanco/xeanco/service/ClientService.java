package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IClientService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Clients;
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
    public Clients save(MultipartFile file, Clients clients) {
        try{
            clients.setClientImgName(file.getOriginalFilename());
            clients.setClientImg(file.getBytes());
            return clientRepository.save(clients);
        }catch(Exception e){
            throw new IntroException("CLientName '" + clients.getClientName() + "Already exist");
        }
    }

    public List<Clients> findAllClients(){
        return clientRepository.findAll();
    }

    public Clients findClientsById(long id){
        Clients clients = clientRepository.findById(id);
        if(clients == null){
            throw new IntroException("Clients with Id: "+ id + " Does not exist");
        }
        return clients;
    }

    public void deleteById(long id){
        Clients clients = clientRepository.findById(id);
        if(clients == null){
            throw new IntroException("Clients with Id: "+ id + " Does not exist");
        }
        clientRepository.delete(clients);
    }
}
