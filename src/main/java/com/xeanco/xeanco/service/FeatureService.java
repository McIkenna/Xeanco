package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IFeatureService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Feature;
import com.xeanco.xeanco.model.FeatureTask;
import com.xeanco.xeanco.repository.FeatureRepository;
import com.xeanco.xeanco.repository.FeatureTaskRepository;
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
    FeatureTaskRepository featureTaskRepository;

    @Override
    public Feature save(MultipartFile file, Feature feature) {
        UUID ranId = UUID.randomUUID();
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
            Feature feature1 = new Feature(featureHeading,
                    featureSubHeading,
                    featureDescription,
                    file.getBytes(),
                    featureImageName,
                    featureType,
                    downloadUrl);
            feature1.setFeatureIdentifier(ranId.toString());
            if(feature1.getFeatureId() == null){
                FeatureTask featureTask = new FeatureTask();
                feature1.setFeatureTask(featureTask);
                featureTask.setFeature(feature1);
                featureTask.setFeatureIdentifier(feature1.getFeatureIdentifier());
                featureTask.setHeadline(feature1.getFeatureHeading());
                featureTask.setFeatureTaskDownloadUrl(feature1.getFeatureDownloadUrl());
                featureTask.setSummary(feature1.getFeatureDescription());
                featureTask.setImage(feature1.getFeatureImage());
                featureTask.setImageType(feature1.getFeatureImageType());
                featureTask.setImageName(feature1.getFeatureImageName());
            }
            if(feature1.getFeatureId() != null){
                feature1.setFeatureTask(featureTaskRepository.findByFeatureIdentifier(feature1.getFeatureIdentifier()));
            }
            return featureRepository.save(feature1);

        }catch (Exception e){
            throw new IntroException("Feature ID " + feature.getFeatureIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Feature findFeatureByIdentifier(String id){
        Feature feature2 = featureRepository.findByFeatureIdentifier(id);
        if(feature2 == null){
            throw new IntroException("Feature with ID: " + id + " Does not exist");
        }
        return feature2;
    }

    public Iterable<Feature> findAllFeature(){
        return featureRepository.findAll();
    }

    public void deleteFeatureByIdentifier(String id){
        Feature feature3 = featureRepository.findByFeatureIdentifier(id);
        if(feature3 == null){
            throw new IntroException("Feature with ID: " + id + " Does not exist");
        }
        featureRepository.delete(feature3);
    }

    /*
    public Feature update(MultipartFile file, Feature feature) {

        String featureImageName = file.getOriginalFilename();
        String featureType = file.getContentType();
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(featureImageName)
                .toUriString();
        String downloadUrl = feature.setFeatureDownloadUrl(downloadUri);
        try{
            feature.setFeatureImageName(featureImageName);
            feature.setFeatureImageType(featureType);
            feature.setFeatureDownloadUrl(downloadUrl);
            feature.setFeatureImage(file.getBytes());
            feature.setFeatureIdentifier(feature.getFeatureIdentifier());

            return featureRepository.save(feature);
        }catch (Exception e){
            throw new IntroException("Feature ID " + feature.getFeatureId() + "' already exists");
        }
    }

     */

    public Feature update(MultipartFile file, Feature feature) {

        String featureImageName = file.getOriginalFilename();
        String featureType = file.getContentType();
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(featureImageName)
                .toUriString();
        String downloadUrl = feature.setFeatureDownloadUrl(downloadUri);
        try{


            feature.setFeatureImageName(featureImageName);
            feature.setFeatureImageType(featureType);
            feature.setFeatureDownloadUrl(downloadUrl);
            feature.setFeatureImage(file.getBytes());


            return featureRepository.save(feature);
        }catch (Exception e){
            throw new IntroException("Feature ID " + feature.getFeatureId() + "' already exists");
        }
    }
}
