package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IClientService {
    Client save(MultipartFile file, Client client);
}
