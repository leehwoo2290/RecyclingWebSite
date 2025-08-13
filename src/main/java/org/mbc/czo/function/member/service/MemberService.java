package org.mbc.czo.function.member.service;


import org.mbc.czo.function.member.dto.MemberFindIDDTO;
import org.mbc.czo.function.member.dto.MemberJoinDTO;

public interface MemberService {
    // 회원가입시 해당아이디가 존재하는 경우 처리

   //중복 아이디 처리, 아이디 존재 안함 Exception
    static class M_idExistException extends Exception {

        // 만일 같은 아이디가 존재하면 예외를 발생
    }


    void join(MemberJoinDTO memberJoinDTO) throws M_idExistException;

    void findID(MemberFindIDDTO memberFindIDDTO) throws M_idExistException;

   // void modifyPw(MemberSecurityDTO memberSecurityDTO);
}
