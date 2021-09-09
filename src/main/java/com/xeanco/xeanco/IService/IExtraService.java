package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.Extra;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IExtraService {
    Extra save(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, Extra extra);
}
