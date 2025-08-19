package org.mbc.czo.function.boarduser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
//1
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt; //등록일
    private LocalDateTime modifiedAt; //수정일

    private List<String> fileNames; //첨부파일 목록

}
