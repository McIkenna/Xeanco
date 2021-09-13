package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IProductTaskService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Product;
import com.xeanco.xeanco.model.ProductLog;
import com.xeanco.xeanco.model.ProductTask;
import com.xeanco.xeanco.repository.ProductLogRepository;
import com.xeanco.xeanco.repository.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductTask saveOrUpdate(MultipartFile file, String productIdentifier, ProductTask productTask) {
        try{
            ProductLog productLog = productLogRepository.findByProductIdentifier(productIdentifier);
            productTask.setProductLog(productLog);
            Integer ProductLogSequence = productLog.getProductSequence();
            ProductLogSequence++;
            productLog.setProductSequence(ProductLogSequence);
            productTask.setProductTskImg(file.getBytes());
            productTask.setProductTskImgName(file.getOriginalFilename());
            productTask.setProductTskImgType(file.getContentType());
            productTask.setProductTskName(productLog.getProduct().getProductName());
            productTask.setProductTskSummary(productLog.getProduct().getProductSummary());
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadProductTask/")
                    .path(productTask.getProductTskImgName())
                    .toUriString();
            productTask.setProductTskDownloadUrl(downloadUri);
            productTask.setProductSequence(productLog.getProductIdentifier() + "-" + ProductLogSequence);
            productTask.setProductIdentifier(productIdentifier);

            return productTaskRepository.save(productTask);

        }catch(Exception e){
            throw new IntroException("Product ID: " + productTask.getProductIdentifier() + " alreadty exist");
        }
    }

    public Iterable<ProductTask> getProductById(String id){
        Product product = productRepository.findByProductIdentifier(id);
        if(product == null){
            throw new IntroException("ProductTask ID: " + id + " Does not exist");
        }
        return productTaskRepository.findByProductIdentifier(id);
    }

    public ProductTask findPTByProductSequence(String productlog_id, String pt_id) {

        //make sure we are searching on an existing backlog
        ProductLog productLog = productLogRepository.findByProductIdentifier(productlog_id);
        if (productLog == null) {
            throw new IntroException("Product with ID: '" + productLog + "' does not exist");
        }

        //make sure that our task exists
        ProductTask productTask = productTaskRepository.findByProductSequence(pt_id);

        if (productTask == null) {
            throw new IntroException("Project Task '" + pt_id + "' not found");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if (!productTask.getProductIdentifier().equals(productlog_id)) {
            throw new IntroException("Project Task '" + pt_id + "' does not exist in project: '" + productlog_id);
        }
        return productTask;
    }

    public ProductTask updateByProductSequence(MultipartFile file, ProductTask updatedTask, String backlog_id, String pt_id){


        updatedTask.setProductTskImgName(file.getOriginalFilename());
        updatedTask.setProductTskImgType(file.getContentType());
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadProductTask/")
                .path(updatedTask.getProductTskImgName())
                .toUriString();
        updatedTask.setProductTskDownloadUrl(downloadUri);
        try{
            updatedTask.setProductTskImg(file.getBytes());
            ProductTask productTask = findPTByProductSequence(backlog_id, pt_id);

            productTask = updatedTask;

            return productTaskRepository.save(productTask);
        }catch(Exception e){
            throw new  IntroException(e.getLocalizedMessage());
        }

    }


    public void deletePTByProductSequence(String backlog_id, String pt_id){
        ProductTask productTask = findPTByProductSequence(backlog_id, pt_id);
        productTaskRepository.delete(productTask);
    }

    public Iterable<ProductTask> getAllProductTask(){
        return productTaskRepository.findAll();
    }

    }
