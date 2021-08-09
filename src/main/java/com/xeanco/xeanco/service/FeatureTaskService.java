package com.xeanco.xeanco.service;

import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.FeatureTask;
import com.xeanco.xeanco.repository.FeatureRepository;
import com.xeanco.xeanco.repository.FeatureTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FeatureTaskService {

    @Autowired
    FeatureRepository featureRepository;
    @Autowired
    FeatureTaskRepository featureTaskRepository;
/*
    @Override
    public FeatureTask save(MultipartFile file, String featureIdentifier, FeatureTask featureTask) {
        try {
            Feature feature = featureRepository.findByFeatureIdentifier(featureIdentifier);
            featureTask.setFeature(feature);
            featureTask.setFeatureIdentifier(featureIdentifier);
            featureTask.setImageName(file.getOriginalFilename());
            featureTask.setImageType(file.getContentType());
            featureTask.setHeadline(feature.getFeatureHeading());
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(featureTask.getImageName())
                    .toUriString();
            featureTask.setFeatureTaskDownloadUrl(downloadUri);
            featureTask.setImage(file.getBytes());

            return featureTaskRepository.save(featureTask);
        }catch(Exception e){
            throw new IntroException("Feature ID " + featureTask.getFeatureIdentifier() + "' already exists");
        }
    }


 */
    public FeatureTask getFeatureTask(String featureId){
        FeatureTask task2 = featureTaskRepository.findByFeatureIdentifier(featureId);
        if(task2 == null){
            throw new IntroException("FeatureTask ID " + featureId + " Does not exist");
        }
        return task2;
    }


    public FeatureTask update(MultipartFile file, FeatureTask featureTask) {

        try {
            featureTask.setImageName(file.getOriginalFilename());
            featureTask.setImageType(file.getContentType());
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(featureTask.getImageName())
                    .toUriString();
            featureTask.setFeatureTaskDownloadUrl(downloadUri);
            featureTask.setImage(file.getBytes());

            return featureTaskRepository.save(featureTask);
        }catch(Exception e){
            throw new IntroException("Feature ID " + featureTask.getFeatureIdentifier() + "' already exists");
        }
    }
}
