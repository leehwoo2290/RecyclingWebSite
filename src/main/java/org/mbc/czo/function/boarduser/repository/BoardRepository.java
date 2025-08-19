package org.mbc.czo.function.boarduser.repository;

//1

import org.mbc.czo.function.boarduser.domain.Board;
import org.mbc.czo.function.boarduser.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
