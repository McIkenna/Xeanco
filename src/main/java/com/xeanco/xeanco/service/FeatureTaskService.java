package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IFeatureTaskService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.FeatureLog;
import com.xeanco.xeanco.model.FeatureTask;
import com.xeanco.xeanco.repository.FeatureLogRepository;
import com.xeanco.xeanco.repository.FeatureTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FeatureTaskService implements IFeatureTaskService{

    @Autowired
    FeatureLogRepository featureLogRepository;
    @Autowired
    FeatureTaskRepository featureTaskRepository;

    @Override
    public FeatureTask saveOrUpdate(MultipartFile file, String featureIdentifier, FeatureTask featureTask) {
        FeatureLog featurelog = featureLogRepository.findByFeatureIdentifier(featureIdentifier);
        featureTask.setFeatureLog(featurelog);

        Integer featureLogSequence = featurelog.getFTSequence();
        featureLogSequence++;
        try {
            featureTask.setImageName(file.getOriginalFilename());
            featureTask.setImageType(file.getContentType());
            featureTask.setFeatureSequence(featureLogSequence.toString());
            featureTask.setHeadline(featurelog.getFeature().getFeatureSubHeading());
            featureTask.setSummary(featurelog.getFeature().getFeatureDescription());
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(featureTask.getImageName())
                    .toUriString();
            featureTask.setFeatureTaskDownloadUrl(downloadUri);
            featureTask.setFeatureSequence(featureIdentifier + "-" + featureLogSequence);
            featureTask.setImage(file.getBytes());
            featureTask.setFeatureIdentifier(featureIdentifier);

            return featureTaskRepository.save(featureTask);
        }catch(Exception e){
            throw new IntroException("Feature ID " + featureTask.getFeatureIdentifier() + "' already exists");
        }
    }

    public FeatureTask getFeatureTask(String featureId){
        FeatureTask task2 = featureTaskRepository.findByFeatureIdentifier(featureId);
        if(task2 == null){
            throw new IntroException("FeatureTask ID " + featureId + " Does not exist");
        }
        return task2;
    }
}
