package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.ProductTask;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IProductTaskService {
    ProductTask saveOrUpdate(MultipartFile file, String productIdentifier, ProductTask productTask);
}
