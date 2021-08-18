package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Extras;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IExtraService {
    Extras save(MultipartFile[] file, Extras extras);
}
