package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.FeatureTask;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IFeatureTaskService {
    FeatureTask saveOrUpdate(MultipartFile file, String featuredId, FeatureTask featureTask);
}
