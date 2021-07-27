package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Feature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IFeatureService {

    Feature saveOrUpdate(MultipartFile file, Feature feature);
}
