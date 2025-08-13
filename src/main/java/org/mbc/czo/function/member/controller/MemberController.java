package org.mbc.czo.function.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.member.dto.MemberFindIDDTO;
import org.mbc.czo.function.member.dto.MemberJoinDTO;
import org.mbc.czo.function.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // final을 붙인 필드로 생성자 만듬.

public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void get_MemberLogin(String error, String logout) {

        // http://localhost/member/login?error=???
        // http://localhost/member/login?logout=???
        log.info("MemberController.loginGet메서드 실행....");
        log.info("logout: " + logout); //데이터베이스에서 활용
        log.info("error: " + error); //데이터베이스에서 활용

        if(logout != null){
            log.info("logout 처리됨!!! : " + logout);
        }
    }

    @GetMapping("/findID")
    public void get_MemberfindID() {
        log.info("MemberController.get_MemberfindID 실행....");
    }

    @PostMapping("/findID")
    public String post_MemberfindID(MemberFindIDDTO memberFindIDDTO, RedirectAttributes redirectAttributes){
        // html에서 넘어오는 데이터 처리용

        log.info("MemberController.post_MemberfindID..... ");
        log.info(memberFindIDDTO);

        try{
            memberService.findID(memberFindIDDTO); // 회원가입 처리됨!!

            //id를 못찾으면 MidExistException예외처리 발생
        }catch(MemberService.M_idExistException e){

            redirectAttributes.addFlashAttribute("error","mid");
            // 회원가입시 id 중복되는 예외처리
            return "redirect:/member/findID";

        }
        redirectAttributes.addFlashAttribute("result","success");
        return "redirect:/member/login";
    }

    @GetMapping("/join")
    public String get_MemberJoin() {
        return "member/join"; // templates/member/login.html
    }

    @PostMapping("/join")
    public String post_MemberJoin(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        // html에서 넘어오는 데이터 처리용

        log.info("MemberController.post_MemberJoin..... ");
        //log.info(memberDTO);

        try{
            memberService.join(memberJoinDTO); // 회원가입 처리됨!!

            //id 중복되면 MidExistException예외처리 발생
        }catch(MemberService.M_idExistException e){

            redirectAttributes.addFlashAttribute("error","mid");
            // 회원가입시 id 중복되는 예외처리
            return "redirect:/member/join"; // 회원가입 페이지로 다시 감.

        }
        redirectAttributes.addFlashAttribute("result","success");
        return "redirect:/member/login"; //회원가입 성공시 로그인 페이지로 이동
    }
}
