package org.mbc.czo.function.boarduser.repository.search;
//1
import org.mbc.czo.function.boarduser.domain.Board;
import org.mbc.czo.function.boarduser.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);


}
