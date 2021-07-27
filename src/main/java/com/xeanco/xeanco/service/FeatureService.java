package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IFeatureService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Feature;
import com.xeanco.xeanco.model.FeatureLog;
import com.xeanco.xeanco.repository.FeatureLogRepository;
import com.xeanco.xeanco.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
public class FeatureService implements IFeatureService {

    @Autowired
    FeatureRepository featureRepository;
    @Autowired
    FeatureLogRepository featureLogRepository;

    @Override
    public Feature saveOrUpdate(MultipartFile file, Feature feature) {
        UUID uniqueId = UUID.randomUUID();

        String  featureHeading = feature.getFeatureHeading();
        String featureSubHeading = feature.getFeatureSubHeading();
        String featureDescription = feature.getFeatureDescription();
        String featureImageName = file.getOriginalFilename();
        String featureType = file.getContentType();
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(featureImageName)
                .toUriString();
        String downloadUrl = feature.setFeatureDownloadUrl(downloadUri);
        try{
            Feature feature1 = new Feature(featureHeading, featureSubHeading, featureDescription, file.getBytes(), featureImageName, featureType, downloadUrl);
            feature1.setFeatureIdentifier(uniqueId.toString());
            if(feature1.getId() == null){
                FeatureLog featureLog = new FeatureLog();
                feature1.setFeatureLog(featureLog);
                featureLog.setFeature(feature1);
                featureLog.setFeatureIdentifier(feature1.getFeatureIdentifier().toUpperCase());

            }
            if(feature1.getId() != null){
                feature1.setFeatureLog(featureLogRepository.findByFeatureIdentifier(feature1.getFeatureIdentifier().toUpperCase()));
            }
            return featureRepository.save(feature1);

        }catch (Exception e){
            throw new IntroException("Feature ID " + feature.getId() + "' already exists");
        }
    }

    public Feature findFeatureById(String id){
        Feature feature2 = featureRepository.findByFeatureIdentifier(id.toUpperCase());
        if(feature2 == null){
            throw new IntroException("Feature with ID: " + id + " Does not exist");
        }
        return feature2;
    }

    public Iterable<Feature> findAllFeature(){
        return featureRepository.findAll();
    }

    public void deleteFeatureById(String id){
        Feature feature3 = featureRepository.findByFeatureIdentifier(id.toUpperCase());
        if(feature3 == null){
            throw new IntroException("Feature with ID: " + id + " Does not exist");
        }
        featureRepository.delete(feature3);
    }
}
