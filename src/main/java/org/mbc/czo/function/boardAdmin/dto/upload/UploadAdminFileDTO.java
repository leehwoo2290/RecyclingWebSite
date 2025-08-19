package org.mbc.czo.function.boardAdmin.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadAdminFileDTO {

    private List<MultipartFile> files;

}
