package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IExtraService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.Extra;
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
    //public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
    @Override
    public Extra save(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4,  Extra extra) {
        //StringBuilder fileNames = new StringBuilder();
        try{
            //Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            // fileNames.append(file.getOriginalFilename()+" ");
                extra.setImgName1(file1.getOriginalFilename());
                extra.setImg1(file1.getBytes());

                extra.setImgName2(file2.getOriginalFilename());
                extra.setImg2(file2.getBytes());

                extra.setImgName3(file3.getOriginalFilename());
                extra.setImg3(file3.getBytes());

                extra.setImgName4(file4.getOriginalFilename());
                extra.setImg4(file4.getBytes());
                return extrasRepository.save(extra);
        }catch (IOException ex){
                throw new IntroException("Feature ID ' already exists");
            }
    }


    public List<Extra> findAllExtra(){
        return extrasRepository.findAll();
    }

    public Extra findExtraById(long id){
        Extra extra = extrasRepository.findById(id);
        if(extra == null){
            throw new IntroException("Extra with Id: "+ id + " Does not exist");
        }
        return extra;
    }

    public void deleteById(long id){
        Extra extra = extrasRepository.findById(id);
        if(extra == null){
            throw new IntroException("Extra with Id: "+ id + " Does not exist");
        }
        extrasRepository.delete(extra);
    }
}
