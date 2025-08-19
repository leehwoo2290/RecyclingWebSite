package org.mbc.czo.function.boardAdmin.repository;

import org.mbc.czo.function.boardAdmin.Search.BoardAdminSearch;
import org.mbc.czo.function.boardAdmin.domain.BoardAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardAdminRepository extends JpaRepository<BoardAdmin, Long>, BoardAdminSearch {

    Page<BoardAdmin> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable); // 제목에 특정 키워드가 존재하는 게시글 찾기

    @Query("select b from BoardAdmin b where b.title like concat('%', : keyword, '%')")
        // 위에 거를 쿼리로 수정
    Page<BoardAdmin> findKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()", nativeQuery = true)
        // 날짜 변화해 주는 쿼리
    String getTime();



}


