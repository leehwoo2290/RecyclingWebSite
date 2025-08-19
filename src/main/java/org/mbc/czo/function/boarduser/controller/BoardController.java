package org.mbc.czo.function.boarduser.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.boarduser.dto.BoardDTO;
import org.mbc.czo.function.boarduser.dto.PageRequestDTO;
import org.mbc.czo.function.boarduser.dto.PageResponseDTO;
import org.mbc.czo.function.boarduser.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/userlist")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

           log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);


    }
}
