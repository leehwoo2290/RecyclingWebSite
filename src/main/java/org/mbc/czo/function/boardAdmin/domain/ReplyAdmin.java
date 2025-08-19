package org.mbc.czo.function.boardAdmin.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 번호 자동 생성
    private Long rno;  // 게시물 번호

    @ManyToOne
    private BoardAdmin boardAdmin;  // 게시글 fk 처리


}
