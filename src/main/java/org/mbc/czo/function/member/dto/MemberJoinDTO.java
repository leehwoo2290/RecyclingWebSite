package org.mbc.czo.function.member.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid;

    private String mname;

    private String mphoneNumber;

    private String memail;

    private String mpassword;

    private String maddress;

    private Long mmileage;

    private boolean misActivate;

    private boolean misSocialActivate;
}
