package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IIntroService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Intro;
import com.xeanco.xeanco.repository.IntroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@Service
public class IntroService implements IIntroService {

    @Autowired
    IntroRepository introRepository;

    @Override
    public Intro saveOrUpdate(MultipartFile file, Intro intro) {
         String introName = intro.getIntroName();
       String introDescription = intro.getIntroDescription();
       String imageName = file.getOriginalFilename();
        String imageType=file.getContentType();
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageName)
                .toUriString();
        String downloadUrl = intro.setImageDownloadUrl(downloadUri);

        try{
            Intro intro1 = new Intro(introName, introDescription, file.getBytes(), imageName, imageType, downloadUrl);
            return introRepository.save(intro1);
        }catch(Exception e){
            throw new IntroException("IntroName '" + intro.getIntroName() + "Already exist");
        }

    }

    public Intro findIntroById(long id){
        Intro intro2 = introRepository.findById(id);
        if(intro2 == null){
            throw new IntroException("Intro with Id" + id + "Does not exist");
        }
        return intro2;
    }

    public void deleteInfoById(long id){
        Intro intro = introRepository.findById(id);
        if(intro == null){
            throw new IntroException("Intro with Id" + id + "Does not exist");
        }
        introRepository.delete(intro);
    }

    public List<Intro> findAllIntro(){
        return introRepository.findAll();
    }


    public Intro Update(MultipartFile file, Intro intro) {
        String imageName = file.getOriginalFilename();
        String imageType=file.getContentType();
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageName)
                .toUriString();
        String downloadUrl = intro.setImageDownloadUrl(downloadUri);

        try{
            intro.setImageName(imageName);
            intro.setImageType(imageType);
            intro.setImageDownloadUrl(downloadUrl);
            intro.setImage(file.getBytes());
            return introRepository.save(intro);
        }catch(Exception e){
            throw new IntroException("IntroName '" + intro.getIntroName() + "Already exist");
        }

    }
}
