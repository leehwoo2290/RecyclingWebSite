package org.mbc.czo.function.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.mbc.czo.function.member.constant.Role;

@Entity
@Table(name="member")
@Getter
@Setter
//@ToString
public class member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //동작 방식 https://rei050r.tistory.com/247 참고
    private Long id;

    @NotNull
    @Column(name="member_name", length = 10)
    private String name;

    @NotNull
    @Column(name="member_phoneNumber", length = 11, unique = true)  // 유니크 처리
    private String phoneNumber;

    @NotNull
    @Column(name="member_email", length = 30, unique = true)  // 유니크 처리
    private String email;  // 회원 검색 처리용

    @NotNull
    @Column(name="member_password", length = 20)
    private String password;

    @NotNull
    @Column(name="member_address", length = 100)
    private String address;

    @Column(name="member_role", length = 5)
    @Enumerated(EnumType.STRING)
    private Role role;  // constant.Role 사용자, 관리자 구분용

    @Column(name="member_mileage")
    private Long mileage;

    @Column(name = "member_isActivate")
    private boolean isActivate;

    @Column(name = "member_isSocialActivate")
    private boolean isSocialActivate;

    @PrePersist //db 저장 전 실행(초기화)
    public void prePersist() {
        this.isActivate = true;
        this.isSocialActivate = false;
    }
}
