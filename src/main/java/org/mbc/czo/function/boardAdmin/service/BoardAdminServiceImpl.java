package org.mbc.czo.function.boardAdmin.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.czo.function.boardAdmin.domain.BoardAdmin;
import org.mbc.czo.function.boardAdmin.dto.BoardAdminDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminRequestDTO;
import org.mbc.czo.function.boardAdmin.dto.PageAdminResponseDTO;
import org.mbc.czo.function.boardAdmin.repository.BoardAdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardAdminServiceImpl implements BoardAdminService {

    private final ModelMapper modelMapper;
    private final BoardAdminRepository boardAdminRepository;

    @Override
    public Long register(BoardAdminDTO boardAdminDTO) {
        BoardAdmin boardAdmin = modelMapper.map(boardAdminDTO, BoardAdmin.class);
        Long bano = boardAdminRepository.save(boardAdmin).getBno();

        return bano;
    }

    @Override
    public BoardAdminDTO readOne(Long bno) {
        Optional<BoardAdmin> result = boardAdminRepository.findById(bno);
        BoardAdmin boardAdmin = result.orElseThrow();
        BoardAdminDTO boardAdminDTO = modelMapper.map(boardAdmin, BoardAdminDTO.class);

        return boardAdminDTO;
    }

    @Override
    public void modify(BoardAdminDTO boardAdminDTO) {
        Optional<BoardAdmin> result = boardAdminRepository.findById(boardAdminDTO.getBno());
        BoardAdmin boardAdmin = result.orElseThrow(); // 예외 발생
        boardAdmin.change(boardAdminDTO.getTitle(), boardAdminDTO.getContent());  // 제목과 내용을 바꾼다
        boardAdminRepository.save(boardAdmin);

    }

    @Override
    public void remove(Long bano) {
        boardAdminRepository.deleteById(bano);
    }

    @Override
    public PageAdminResponseDTO<BoardAdminDTO> list(PageAdminRequestDTO pageAdminRequestDTO) {
        String[] types = pageAdminRequestDTO.getTypes();
        String keyword = pageAdminRequestDTO.getKeyword();
        Pageable pageable = pageAdminRequestDTO.getPageable("bno");

        Page<BoardAdmin> result = boardAdminRepository.searchAll(types, keyword, pageable);
        /*return null;*/

        List<BoardAdminDTO> dtoList = result.getContent().stream()
                .map(boardAdmin -> modelMapper.map(boardAdmin, BoardAdminDTO.class))
                .collect(Collectors.toList());

        return PageAdminResponseDTO.<BoardAdminDTO>withAll()
                .pageAdminRequestDTO(pageAdminRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }




}
