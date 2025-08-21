package org.mbc.czo.function.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.image.service.ImageUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    //RestAPI  REST 스타일은 URL이 행동이 아닌 자원을 표현, HTTP 상태 코드로 성공/실패를 전달
    //json 활용한 데이터전달
    //json전달 형태
//    {
//        "files": ["url1.jpg", "url2.png"],
//        "message": "업로드 및 DB 저장 성공"
//    }
    //static/js/uploadImages.js 참고

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam Map<String, String> extraData
    ) {
        try {
            List<String> urls = imageUploadService.uploadAndSaveImages(files, extraData);

            Map<String, Object> response = new HashMap<>();
            response.put("files", urls);
            response.put("message", "업로드 및 DB 저장 성공");
            return ResponseEntity.ok(response);

        }catch (IOException e)  {
            log.error("파일 업로드 중 오류 발생", e);  // 로그 파일에 기록
            return ResponseEntity.internalServerError().body("파일 업로드 실패");
        }
    }
}
