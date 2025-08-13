package org.mbc.czo.function.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.mbc.czo.function.member.constant.Role;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="member")
@Getter
@Setter
@Builder
@ToString

public class Member {

    @Id
    @NotNull
    @Column(name="member_id", length = 20)
    private String m_id;

    @NotNull
    @Column(name="member_name", length = 10)
    private String m_name;

    @NotNull
    @Column(name="member_phoneNumber", length = 11, unique = true)  // 유니크 처리
    private String m_phoneNumber;

    @NotNull
    @Column(name="member_email", length = 30, unique = true)  // 유니크 처리
    private String m_email;  // 회원 검색 처리용

    @NotNull
    @Column(name="member_password", length = 255) //암호화 시 글자 수 늘어남에 따른 length 증가
    private String m_password;

    @NotNull
    @Column(name="member_address", length = 100)
    private String m_address;

    // 회원롤 관리(user,admin)
    @Column(name="member_roleSet")
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Role> m_roleSet = new HashSet<Role>();

    @Column(name="member_mileage")
    private Long m_mileage;

    @Column(name = "member_isActivate")
    private boolean m_isActivate;

    @Column(name = "member_isSocialActivate")
    private boolean m_isSocialActivate;

    public void addRole(Role role) {
        this.m_roleSet.add(role);
    }

    @PrePersist //db 저장 전 실행(초기화)
    public void prePersist() {
        this.m_isActivate = true;
        this.m_isSocialActivate = false;
    }
}
