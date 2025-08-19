package org.mbc.czo.function.boardAdmin.controller;


import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.boardAdmin.dto.upload.UploadAdminFileDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Log4j2
public class UpDownController {


    @Value("${org.mbc.upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload (UploadAdminFileDTO uploadFileDTO){
        log.info(uploadFileDTO);
        return null;
    }
}
