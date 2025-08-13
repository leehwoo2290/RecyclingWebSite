package org.mbc.czo.function.member.security.dto;

import org.mbc.czo.function.member.constant.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class MemberSecurityDTO extends User {

    private String m_id;

    private String m_email;

    private String m_password;

    private boolean m_isActivate;

    private boolean m_isSocialActivate;

    // 생성자
    public MemberSecurityDTO(String username, String password, String email,
                             boolean del, boolean social,
                             Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);  // User 객체

        this.m_id = username;
        this.m_password = password;
        this.m_email = email;
        this.m_isActivate = del;
        this.m_isSocialActivate = social;
    }
}
