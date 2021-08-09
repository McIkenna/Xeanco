package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IProductTaskService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.ProductLog;
import com.xeanco.xeanco.model.ProductTask;
import com.xeanco.xeanco.repository.ProductLogRepository;
import com.xeanco.xeanco.repository.ProductTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProductTaskService implements IProductTaskService {

    @Autowired
    ProductTaskRepository productTaskRepository;
    @Autowired
    ProductLogRepository productLogRepository;

    @Override
    public ProductTask saveOrUpdate(MultipartFile file, String productIdentifier, ProductTask productTask) {
        ProductLog productLog = productLogRepository.findByProductIdentifier(productIdentifier);
        productTask.setProductLog(productLog);

        Integer productLogSequence = productLog.getProductSequence();
        productLogSequence++;
        try{
            productTask.setProductTskImg(file.getBytes());
            productTask.setProductTskImgName(file.getOriginalFilename());
            productTask.setProductTskImgType(file.getContentType());
            productTask.setProductSequence(productLogSequence.toString());
            productTask.setProductTskName(productLog.getProduct().getProductName());
            productTask.setProductTskSummary(productLog.getProduct().getProductSummary());
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadProductTask/")
                    .path(productTask.getProductTskImgName())
                    .toUriString();
            productTask.setProductTskDownloadUrl(downloadUri);
            productTask.setProductSequence(productIdentifier + "-" + productLogSequence);
            productTask.setProductIdentifier(productIdentifier);

            return productTaskRepository.save(productTask);

        }catch(Exception e){
            throw new IntroException("Product ID: " + productTask.getProductIdentifier() + " alreadty exist");
        }
    }

    public ProductTask getProductById(String id){
        ProductTask task2 = productTaskRepository.findByProductIdentifier(id.toUpperCase());
        if(task2 == null){
            throw new IntroException("ProductTask ID: " + id + " Does not exist");
        }
        return task2;
    }

}
