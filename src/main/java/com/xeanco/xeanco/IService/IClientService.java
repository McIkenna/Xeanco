package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Clients;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IClientService {
    Clients save(MultipartFile file, Clients clients);
}
