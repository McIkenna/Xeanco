package com.xeanco.xeanco.IService;


import com.xeanco.xeanco.model.Intro;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IIntroService {
    Intro saveOrUpdate(MultipartFile file, Intro intro);
}
