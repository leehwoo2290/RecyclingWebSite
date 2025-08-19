package org.mbc.czo.function.boardAdmin.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAdminResponseDTO<E> {

    private int page;   // 현재 페이지,
    private int size;   // 페이지당 게시물 수
    private int total;  //  총 게시물

    private int start;  // 시작 페이지
    private int end;  // 끝 페이지

    private boolean prev;  // 이전 페이지 존재 여부
    private boolean next;  // 다음 페이지 존재 여부

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageAdminResponseDTO(PageAdminRequestDTO pageAdminRequestDTO, List<E> dtoList, int total){

        if (total <= 0){
            return;
        }
        this.page = pageAdminRequestDTO.getPage();
        this.size = pageAdminRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;  // 화면에서 마지막 번호
        this.start = this.end - 9 ; // 화면에서의 시작 번호

        int last = (int)(Math.ceil((total/(double)size))); // 데이터 계수를 계산한 마지막 페이지
        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}
