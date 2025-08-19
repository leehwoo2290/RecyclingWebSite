package org.mbc.czo.function.boardAdmin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor  // 엔티티 기본 베이스
@ToString()  // 보드 테이블 제외
public class BoardAdminImage implements Comparable<BoardAdminImage>{
    // 순번에 맞게 정렬하기 위해 넣음

    @Id  // pk
    private String Id;
    private String fileName; // 파일 이름
    private int ord;

    @ManyToOne  // fk 선언
    private BoardAdmin boardAdmin;

    @Override
    public int compareTo(BoardAdminImage other) {
        return this.ord  - other.ord;   // ???
    }
    public void changBoard(BoardAdmin boardAdmin) {this.boardAdmin = boardAdmin;}  // boardAdmin 엔티티 변경시 같이 변경
}

