package org.mbc.czo.function.boardAdmin.domain;


import jakarta.persistence.*;
import lombok.*;
import org.mbc.czo.function.common.entity.BaseAdminEntity;

@Entity  // 엔티티라는 뜻
@Getter
@Setter
@AllArgsConstructor  // 모든 필드 값으로 생성자 생성
@NoArgsConstructor  // 기본 생성자
@ToString
@Builder
public class BoardAdmin extends BaseAdminEntity {  // 부모 객체 상속

    @Id  // pk로 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동번호 생성
    private Long bno;  // 공지사항 게시물 번호

    @Column(length =  500, nullable = false)  // 제목 500 바이트 이내, 널 안 됨
    private String title;  // 공지사항 게시물 제목

    @Column(length = 2000, nullable = false)
    private String content;  // 공지사항 게시물 내용

    @Column(length = 50, nullable = false)
    private String writer;  // 공지사항 게시물 작성자


    public void change(String title, String content){  // 제목과 내용만 수정하는 메서드
        this.title = title;  // 제목
        this.content = content;  // 내용
    }





}
