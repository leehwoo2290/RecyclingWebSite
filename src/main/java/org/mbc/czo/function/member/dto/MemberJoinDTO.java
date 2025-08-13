package org.mbc.czo.function.member.dto;

import lombok.Data;

import org.mbc.czo.function.member.constant.Role;

@Data
public class MemberJoinDTO {

    private String m_id;

    private String m_name;

    private String m_phoneNumber;

    private String m_email;

    private String m_password;

    private String m_address;

    private Long m_mileage;

    private boolean m_isActivate;

    private boolean m_isSocialActivate;
}
