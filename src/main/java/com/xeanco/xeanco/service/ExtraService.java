package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IExtraService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Extras;
import com.xeanco.xeanco.repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExtraService implements IExtraService {

    @Autowired
    ExtrasRepository extrasRepository;
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
    @Override
    public Extras save(MultipartFile[] files, Extras extras) {
        //StringBuilder fileNames = new StringBuilder();
        int count =0;
        try{
        for(MultipartFile file: files) {
            String fileName = file.getOriginalFilename();
            //Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            // fileNames.append(file.getOriginalFilename()+" ");

            if (count == 0) {
                extras.setImgName1(fileName);
                extras.setImg1(file.getBytes());
            }
            if (count == 1) {
                extras.setImgName2(fileName);
                extras.setImg2(file.getBytes());
            }
            if (count == 2) {
                extras.setImgName3(fileName);
                extras.setImg3(file.getBytes());
            }
            if (count == 3) {
                extras.setImgName4(fileName);
                extras.setImg4(file.getBytes());
            }
            count++;
        }
            return extrasRepository.save(extras);
        }catch (IOException ex){
                throw new IntroException("Feature ID ' already exists");
            }
    }


    public List<Extras> findAllExtra(){
        return extrasRepository.findAll();
    }

    public Extras findExtraById(long id){
        Extras extra = extrasRepository.findById(id);
        if(extra == null){
            throw new IntroException("Extra with Id: "+ id + " Does not exist");
        }
        return extra;
    }

    public void deleteById(long id){
        Extras extra = extrasRepository.findById(id);
        if(extra == null){
            throw new IntroException("Extra with Id: "+ id + " Does not exist");
        }
        extrasRepository.delete(extra);
    }
}
