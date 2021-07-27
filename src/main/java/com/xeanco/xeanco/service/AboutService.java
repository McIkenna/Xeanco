package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IAboutService;
import com.xeanco.xeanco.exception.IntroException;
import com.xeanco.xeanco.model.About;
import com.xeanco.xeanco.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService implements IAboutService {

    @Autowired
    AboutRepository aboutRepository;

    @Override
    public About saveOrUpdate(About about) {
        try {
           About abt = aboutRepository.save(about);
           return abt;
        } catch (Exception e)
        {
          throw new IntroException("Something went wrong while saving");
        }
    }

    public About findById(long id){
        About abt2 = aboutRepository.findById(id);
        if(abt2 == null){
            throw new IntroException("This item with ID " + id + " Does not exist");
        }
        return abt2;
    }

    public void deleteById(long id){
     aboutRepository.deleteById(id);
    }

    public List<About> findAllItem(){
        return aboutRepository.findAll();
    }
}
