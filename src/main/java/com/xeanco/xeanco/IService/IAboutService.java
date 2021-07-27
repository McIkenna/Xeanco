package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.About;
import org.springframework.stereotype.Component;

@Component
public interface IAboutService {
    About saveOrUpdate(About about);
}
