package org.mbc.czo.function.boardAdmin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.boardAdmin.dto.BoardAdminDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminRequestDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminResponseDTO;
import org.mbc.czo.function.boardAdmin.service.BoardAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardAdminController {


    private final BoardAdminService boardAdminService;


    @GetMapping("/list")
    public void list(PageAdminRequestDTO pageAdminRequestDTO, Model model){
        PageAdminResponseDTO<BoardAdminDTO> responseDTO = boardAdminService.list(pageAdminRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardAdminDTO boardAdminDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("보드 포스트 레지스터................");
        if (bindingResult.hasErrors()){
            log.info("하스 에러......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info(boardAdminDTO);
        Long bno = boardAdminService.register(boardAdminDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageAdminRequestDTO pageAdminRequestDTO, Model model){
        BoardAdminDTO boardAdminDTO = boardAdminService.readOne(bno);
        log.info(boardAdminDTO);
        model.addAttribute("dto", boardAdminDTO);
    }

    @PostMapping("/modify")
    public String modify(PageAdminRequestDTO pageAdminRequestDTO, @Valid BoardAdminDTO boardAdminDTO,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("보드 수정 포스트....." + boardAdminDTO);
        if(bindingResult.hasErrors()){
            log.info("에러,,");
            String link = pageAdminRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bno", boardAdminDTO.getBno());
            return "redirect:/board/modify?"+link;
        }
        boardAdminService.modify(boardAdminDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addFlashAttribute("bno", boardAdminDTO.getBno());
        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("지운다 포스트.." + bno);
        boardAdminService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }

}
