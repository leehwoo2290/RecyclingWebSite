package org.mbc.czo.function.boardAdmin.service;

import org.mbc.czo.function.boardAdmin.dto.BoardAdminDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminRequestDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoardAdminService {

    Long register(BoardAdminDTO boardAdminDTO);  // 글 작성

    BoardAdminDTO readOne(Long bno);  // 상세 보기

    void modify(BoardAdminDTO boardAdminDTO); // 수정하기

    void remove(Long bno);

    PageAdminResponseDTO<BoardAdminDTO> list(PageAdminRequestDTO pageAdminRequestDTO);


        }


